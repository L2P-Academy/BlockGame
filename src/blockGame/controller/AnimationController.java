// Marc & Dirk
package blockGame.controller;

import blockGame.model.AnimationsModel;

// The controller connects the game loop to the animation model
// It decides when the animation should advance (based on isAnimating)

public class AnimationController implements Updatable {
	private final AnimationsModel model;
	private boolean isAnimating = true;

	// Constructor: we pass in the model this controller should control
	public AnimationController(AnimationsModel model) {
		this.model = model;
	}

	// This method is called by the game loop every frame
	@Override
	public void update() {
		if (isAnimating) {
			model.advanceFrame(); // tell the model to move to the next frame
		}
	}

	// Optionally used to control whether the animation is running or not
	public void startAnimation() {
		isAnimating = true;
	}

	public void stopAnimation() {
		isAnimating = false;
	}
}
