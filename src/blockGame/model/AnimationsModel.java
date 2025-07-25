// Marc & Dirk

package blockGame.model;


//The model holds the current state of an animation (e.g., which frame is shown) 

public class AnimationsModel {
	
		private int currentFrame = 0;       // which frame we're currently on
		private final int totalFrames = 4;  // total number of animation frames (looped)

 // This method moves to the next frame, cycling back to 0 when reaching the end
 
public void advanceFrame() {
		currentFrame = (currentFrame + 1) % totalFrames;
		System.out.println("Current animation frame: frame" + currentFrame);
 }
}

