package blockGame; 

import blockGame.view.StartMenuView; 				
import blockGame.controller.SoundController; 		

public class Launcher {
	public static SoundController globalSoundController;

    public static void main(String[] args) {
    	globalSoundController = new SoundController();
        new StartMenuView(globalSoundController);
        
    }
}
