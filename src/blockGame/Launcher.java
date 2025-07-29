package blockGame; 

import blockGame.view.StartMenuView; 				
import blockGame.controller.SoundController; 		

public class Launcher { 							

    public static void main(String[] args) {		       		  	
        new StartMenuView();
        SoundController globalSoundController = new SoundController();
        	globalSoundController.playMusicLoop("/res/sounds/circuit_lofi_key.wav");
        	
    }
}
