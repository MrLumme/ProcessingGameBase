package lumCode.processingGameBase;

import lumCode.processingGameBase.keys.Input;
import lumCode.processingGameBase.keys.InputTracker;
import processing.core.PApplet;
import processing.core.PConstants;

public class Main extends PApplet {

	// ---------
	// CONSTANTS
	// ---------

	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;

	// --------------
	// GAME VARIABLES
	// --------------

	public static boolean doTick = false;

	public static void main(String[] args) {
		main("lumCode.processingGameBase.Main");
	}

	// --------
	// SETTINGS
	// --------

	@Override
	public void settings() {
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
		noSmooth();
	}

	// -----
	// SETUP
	// -----

	@Override
	public void setup() {

	}

	// ----
	// DRAW
	// ----

	@Override
	public void draw() {

	}

	// -----
	// INPUT
	// -----

	@Override
	public void mousePressed() {
		if (mouseButton == PConstants.LEFT) {
			InputTracker.state(Input.INTERACT, true);
		}
	}

	@Override
	public void mouseReleased() {
		if (mouseButton == PConstants.LEFT) {
			InputTracker.state(Input.INTERACT, false);
		}
	}

	@Override
	public void keyPressed() {
		if (key == 'a' || keyCode == PConstants.LEFT) {
			InputTracker.state(Input.LEFT, true);
		} else if (key == 'w' || keyCode == PConstants.UP) {
			InputTracker.state(Input.UP, true);
		} else if (key == 'd' || keyCode == PConstants.RIGHT) {
			InputTracker.state(Input.RIGHT, true);
		} else if (key == 's' || keyCode == PConstants.DOWN) {
			InputTracker.state(Input.DOWN, true);
		}
	}

	@Override
	public void keyReleased() {
		if (key == 'a' || keyCode == PConstants.LEFT) {
			InputTracker.state(Input.LEFT, false);
		} else if (key == 'w' || keyCode == PConstants.UP) {
			InputTracker.state(Input.UP, false);
		} else if (key == 'd' || keyCode == PConstants.RIGHT) {
			InputTracker.state(Input.RIGHT, false);
		} else if (key == 's' || keyCode == PConstants.DOWN) {
			InputTracker.state(Input.DOWN, false);
		}
	}

	// ---------
	// UTILITIES
	// ---------

}
