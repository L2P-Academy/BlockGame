package blockGame; 

import blockGame.view.StartMenuView; 				
import blockGame.controller.SoundController;
import blockGame.controller.XMLController; 		

/**
 * The standard launcher class, calling SoundController and StartMenuView.
 * Uses the Main-Method.
 */
public class Launcher {
	public static SoundController globalSoundController;
    public static void main(String[] args) {
    	globalSoundController = new SoundController();
    	Object[] settings = XMLController.readSettingsFromXML();
    	globalSoundController.setVolume((int)settings[0]);
        new StartMenuView(globalSoundController);
    }
}
