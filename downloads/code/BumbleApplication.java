public class BumbleApplication extends Application {

	//Declare the Scoreloop Client
	private static Client client;

	static void init(final Context android_game_context) {
		if (client == null) {
			client = new Client(android_game_context,
					"gameSecret", null); 
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		init(this);
		ScoreloopManagerSingleton.init(this, 
				"gameSecret");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ScoreloopManagerSingleton.destroy();
	}
}
