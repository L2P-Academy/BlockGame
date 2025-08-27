package blockGame; 

import blockGame.view.StartMenuView; 				
import blockGame.controller.SoundController; 		

/**
 * The standard launcher class, calling SoundController and StartMenuView.
 * Uses the Main-Method.
 */
public class Launcher {
	public static SoundController globalSoundController;

    public static void main(String[] args) {
    	globalSoundController = new SoundController();
    	//probably not necessary - test reasons
//    	SoundController.setInstance(globalSoundController);
        new StartMenuView(globalSoundController);
    }
}
