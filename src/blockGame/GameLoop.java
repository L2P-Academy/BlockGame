// MT , DJ

GameLoop.java
package blockGame.loop;

import blockGame.common.Updatable;

/* This is a custom game loop that runs in its own thread (independent of main UI thread) */

public class GameLoop extends Thread {
    private boolean running = true;

    private final int targetFPS = 2; // Target framerate (slow on purpose for demo)
    private final long frameTime = 1000 / targetFPS; // milliseconds between frames

    private final Updatable controller; // This is the thing we'll update every frame

    // Constructor: we pass in something (like the animation controller) to update
    public GameLoop(Updatable controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        // This method starts running when we call .start() on this thread
        while (running) {
            long startTime = System.currentTimeMillis();

            controller.update(); // Tell the controller to update the animation

            // Calculate how long to sleep to keep a steady frame rate
            long elapsed = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsed;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // Pause for the rest of the frame time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupt flag
                }
            }
        }
    }

    // Allows us to stop the loop from outside
    public void stopLoop() {
        running = false;
    }
}
