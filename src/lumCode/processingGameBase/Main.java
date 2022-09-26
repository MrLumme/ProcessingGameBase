package lumCode.processingGameBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Properties;

import lumCode.interactables.entities.Button;
import lumCode.interactables.entities.Label;
import lumCode.processingGameBase.keys.Input;
import lumCode.processingGameBase.keys.InputTracker;
import lumCode.processingGameBase.sound.SoundKeeper;
import lumCode.processingGameBase.sound.types.SFXType;
import lumCode.processingGameBase.time.TimeKeeper;
import lumCode.utils.ExMath;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

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
	public static Properties prop = new Properties();

	// Example - Start
	private static int buttonClicks = 0;
	private static Button button;
	private static Label label;
	private static PFont font;
	// Example - End

	// ----
	// MAIN
	// ----

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
		frameRate(Settings.FRAME_RATE);

		SoundKeeper sk = SoundKeeper.getInstance();
		TimeKeeper tk = TimeKeeper.getInstance();

		loadProperties();

		// Example - Start
		font = loadFont(Settings.FONT_PATH + "default.vlw");
		button = new Button(SCREEN_WIDTH / 2 - 50, SCREEN_HEIGHT / 2 - 25, 100, 50, this, "Press me", font) {
			@Override
			public void action() {
				SoundKeeper.playEffect(SFXType.BUTTON);
				buttonClicks++;
			}
		};
		label = new Label(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 + 50, this, "Clicked: " + buttonClicks, font);
		// Example - End

		sk.start();
		tk.start();
	}

	// ----
	// DRAW
	// ----

	@Override
	public void draw() {
		background(0);

		// Example - Start
		button.draw();
		label.setText("Clicked: " + buttonClicks);
		label.draw();
		// Example - End
	}

	// ----
	// EXIT
	// ----

	@Override
	public void exit() {
		super.exit();
		saveProperties();
	}

	// -----
	// INPUT
	// -----

	@Override
	public void mouseClicked() {
		// Example - Start
		button.mouseClicked();
		// Example - End
	}

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

	public static void loadProperties() {
		try {
			prop.load(new FileReader(Settings.PROPERTIES_PATH));

			SoundKeeper.setMasterVolume(
					ExMath.clamp(Double.parseDouble(prop.getProperty("master_vol", "" + SoundKeeper.getMasterVolume())),
							0.000, 1.000));
			SoundKeeper.setEffectVolume(ExMath.clamp(
					Double.parseDouble(prop.getProperty("effects_vol", "" + SoundKeeper.getEffectVolume())), 0.000,
					1.000));
			SoundKeeper.setMusicVolume(
					ExMath.clamp(Double.parseDouble(prop.getProperty("music_vol", "" + SoundKeeper.getMusicVolume())),
							0.000, 1.000));
			SoundKeeper.setVoiceVolume(
					ExMath.clamp(Double.parseDouble(prop.getProperty("voice_vol", "" + SoundKeeper.getVoiceVolume())),
							0.000, 1.000));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveProperties() {
		try {
			DecimalFormatSymbols dc = new DecimalFormatSymbols();
			dc.setDecimalSeparator('.');
			NumberFormat format = new DecimalFormat("0.000", dc);

			prop.setProperty("master_vol", format.format(SoundKeeper.getMasterVolume()));
			prop.setProperty("effect_vol", format.format(SoundKeeper.getEffectVolume()));
			prop.setProperty("music_vol", format.format(SoundKeeper.getMusicVolume()));
			prop.setProperty("voice_vol", format.format(SoundKeeper.getVoiceVolume()));

			prop.store(new FileWriter(Settings.PROPERTIES_PATH), "saved-" + System.currentTimeMillis());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
