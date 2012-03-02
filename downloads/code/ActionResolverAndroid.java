public class ActionResolverAndroid implements ActionResolver {
	Handler uiThread;
	BumbleAndroid mContext; //Your class here which extends AndroidApplication
	ScoreloopHandler handler;
	
	public ActionResolverAndroid(BumbleAndroid mContext) {
		uiThread = new Handler(); //This binds the handler to the "main" thread, see documentation of handler
		this.mContext = mContext;
		handler = new ScoreloopHandler(mContext);
	}

	@Override
	public void showScoreloop() {
		Intent intent = new Intent(mContext, EntryScreenActivity.class);
		mContext.startActivity(intent);
	}
	
	@Override
	public void submitScore(final int mode, final int score) {
		uiThread.post(new Runnable() {
			@Override
			public void run() {
				
				handler.submitScore(score);
				handler.getRankingForScore(score);
			}
		});
	}
	
	@Override
	public void refreshScores() {
		uiThread.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mContext, "Refreshing scores", Toast.LENGTH_SHORT).show();
				handler.getGlobalHighscores();
				handler.getTodayHighscores();
			}
		});
	}
	
	@Override
	public void bootstrap() {
		uiThread.post(new Runnable() {
			@Override
			public void run() {
				handler.getGlobalHighscores();
				handler.getTodayHighscores();
				
				//Upload local scores, if any
				ScoreloopManagerSingleton.get().submitLocalScores(null);
			}
		});
	}
}
