public class BumbleGame extends Game {

	ActionResolver actionResolver;
	
	MainMenuScreen mainMenuScreen;
	
	
	public BumbleGame(ActionResolver resolver) {
		this.actionResolver = resolver;
	}
	
	@Override
	public void create() {
		Assets.load();
		
		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
		
		if(actionResolver != null)
			actionResolver.bootstrap();
	}
	
	public void saveScore(int score) {
		//Submit to score loop
		if(actionResolver != null)
			actionResolver.submitScore(0, score);
	}

  public void showScoreloop() {
    if(actionResolver != null) {
      actionResolver.showScoreloop();
    }
  }
}
