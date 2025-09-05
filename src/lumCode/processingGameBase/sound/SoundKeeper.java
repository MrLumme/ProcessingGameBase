package lumCode.processingGameBase.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import lumCode.processingGameBase.Main;
import lumCode.processingGameBase.Settings;

/**
 * Static class to manage sounds. Extends Thread.
 * 
 * @author Lumme
 * @version 0.1
 * @since 24-09-2019
 */

public final class SoundKeeper extends Thread {
	public static SoundKeeper instance = null;

	private static ArrayList<AudioPlayer> effects = new ArrayList<>();
	private static AudioPlayer info = null;
	private static AudioPlayer song = null;

	private static boolean muted = false;
	private static boolean randomizeBackgrounds = false;

	private static double masterVolume = 0.5;
	private static double musicVolume = 1.0;
	private static double effectsVolume = 1.0;
	private static double voiceVolume = 1.0;

	private static final ArrayList<Sound> queue = new ArrayList<>();

	/**
	 * Constructor. Can not be used outside to class. Sets the class to be a daemon
	 * thread.
	 */

	private SoundKeeper() {
		setDaemon(true);
		setName("SoundKeeper");
	}

	/**
	 * Overridden method of super class Thread. Main running method.
	 */

	@Override
	public void run() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// Do nothing
		}

		File sd = new File(Settings.SOUND_PATH);
		if (sd.exists()) {
			if (!muted) {
				while (!Main.doTick) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// Do nothing
					}
				}
			}
			while (Main.doTick) {
				if (!muted) {
					// Play info audio
					if (info == null) {
						if (!queue.isEmpty()) {
							Sound war = queue.remove(0);
							war.setPlaying(true);
							info = new AudioPlayer(war.getPlaySetup());
							info.setVol(masterVolume * voiceVolume);
							info.start();
						}
					} else if (!info.isAlive() || info.isHalted()) {
						info = null;
					} else if (info.isErrored() && info.isAlive()) {
						info.halt();
					}

					// Play background
					if (song == null || !song.isAlive() || song.isHalted()) {
						File next = null;
						// SoundFactory.getNewBackground(song);
						if (next != null) {
							song = new AudioPlayer(next);
							song.setVol(masterVolume * musicVolume / 4);
							song.start();
						}
					} else if (song.isErrored() && song.isAlive()) {
						song.halt();
					}

					try {
						Thread.sleep(Settings.SOUND_DELAY);
					} catch (InterruptedException e) {
						// Do nothing
					}
				} else {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// Do nothing
					}
				}
			}
		}
	}

	/**
	 * Initializes the the class by getting the instance and starting it, before
	 * returning the instance.
	 * 
	 * @return SoundKeeper
	 */

	public static SoundKeeper init() {
		SoundKeeper o = getInstance();
		o.start();
		return o;
	}

	/**
	 * Returns the instance of the class.
	 * 
	 * @return SoundKeeper
	 */

	public static SoundKeeper getInstance() {
		if (instance == null) {
			instance = new SoundKeeper();
			instance.setDaemon(true);
		}
		return instance;
	}

	/**
	 * Adds a Sound to the correct queue
	 * 
	 * @param sound
	 */

	public static void addSoundToQueue(Sound sound) {
		if (sound != null) {
			queue.add(sound);
		}
	}

	/**
	 * Plays a given sound effect type at a given volume.
	 * 
	 * @param type
	 * @param vol
	 */

	public static void playEffect(SFXType type, double vol) {
		if (!muted) {
			Iterator<AudioPlayer> it = effects.iterator();
			while (it.hasNext()) {
				AudioPlayer sfx = it.next();
				if (!sfx.isAlive() || sfx.isHalted()) {
					it.remove();
				} else if (sfx.isErrored()) {
					sfx.halt();
					it.remove();
				}
			}
			if (effects.size() < Settings.SFX_LIMIT) {
				AudioPlayer sfx = new AudioPlayer(SFXType.getFile(type));
				sfx.setVol(vol * masterVolume * effectsVolume);
				sfx.start();
				effects.add(sfx);
			}
		}
	}

	/**
	 * Overloaded method that plays a given sfx type at 60% volume.
	 * 
	 * @param type
	 */

	public static void playEffect(SFXType type) {
		playEffect(type, 1.0);
	}

	/**
	 * @return boolean
	 */

	public static boolean isMuted() {
		return muted;
	}

	/**
	 * @param muted
	 */

	public static void setMuted(boolean muted) {
		SoundKeeper.muted = muted;
	}

	public static double getMasterVolume() {
		return masterVolume;
	}

	public static void setMasterVolume(double masterVolume) {
		SoundKeeper.masterVolume = masterVolume;
		SoundKeeper.muted = masterVolume < 0.01 ? true : false;
	}

	public static double getMusicVolume() {
		return musicVolume;
	}

	public static void setMusicVolume(double musicVolume) {
		SoundKeeper.musicVolume = musicVolume;
	}

	public static double getEffectVolume() {
		return effectsVolume;
	}

	public static void setEffectVolume(double sfxVolume) {
		SoundKeeper.effectsVolume = sfxVolume;
	}

	public static double getVoiceVolume() {
		return voiceVolume;
	}

	public static void setVoiceVolume(double voiceVolume) {
		SoundKeeper.voiceVolume = voiceVolume;
	}

	public static boolean isRandomizeBackgrounds() {
		return randomizeBackgrounds;
	}

	public static void setRandomizeBackgrounds(boolean randomizeBackgrounds) {
		SoundKeeper.randomizeBackgrounds = randomizeBackgrounds;
	}

}
