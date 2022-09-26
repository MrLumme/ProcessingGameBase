package lumCode.processingGameBase.sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import lumCode.utils.ExMath;

/**
 * Class to play sounds without holding other parts of the game back. Extends
 * Thread.
 * 
 * @author Lumme
 * @version 0.1
 * @since 24-09-2019
 */

public class AudioPlayer extends Thread {
	private static final int BUFFER_SIZE = 128000;
	private final File[] sounds;
	private double vol;
	private boolean paused;
	private boolean halted;
	private boolean errored;
	private SourceDataLine sourceLine;

	/**
	 * Constructor. Sets sounds to play.
	 * 
	 * @param sounds
	 */

	public AudioPlayer(File[] sounds) {
		setDaemon(true);
		setName("AudioPlayer");

		this.sounds = sounds;
		sourceLine = null;
		errored = false;
		paused = false;
		halted = false;
		vol = 0.5;
	}

	/**
	 * Constructor. Overloads method to enable only using one file.
	 * 
	 * @param sound
	 */

	public AudioPlayer(File sound) {
		this(new File[] { sound });
	}

	@Override
	public void run() {
		// validate files
		for (File s : sounds) {
			if (!s.exists()) {
				errored = true;
				try {
					throw new FileNotFoundException(s.getAbsolutePath() + "could not be found!");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		if (!errored) {
			AudioInputStream[] streams = new AudioInputStream[sounds.length];
			AudioFormat[] formats = new AudioFormat[streams.length];

			try {
				for (int i = 0; i < sounds.length; i++) {
					streams[i] = AudioSystem.getAudioInputStream(sounds[i]);
					formats[i] = streams[i].getFormat();
				}
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
				errored = true;
			} catch (IOException e) {
				e.printStackTrace();
				errored = true;
			}

			if (!errored) {

				for (int i = 0; i < sounds.length; i++) {
					try {
						DataLine.Info info = new DataLine.Info(SourceDataLine.class, formats[i]);

						sourceLine = (SourceDataLine) AudioSystem.getLine(info);
						sourceLine.open(formats[i]);

						sourceLine.start();

						if (sourceLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
							((FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN))
									.setValue((float) ExMath.map(vol, 0.0, 1.0, -30.00, 6.0206));
						}

						int nBytesRead = 0;
						byte[] abData = new byte[BUFFER_SIZE];
						while (nBytesRead != -1 && !halted) {
							while (paused) {
								try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									// Do nothing
								}
							}
							nBytesRead = streams[i].read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								sourceLine.write(abData, 0, nBytesRead);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
						errored = true;
					} catch (LineUnavailableException e) {
						e.printStackTrace();
						errored = true;
					}

					if (sourceLine != null) {
						sourceLine.drain();
						sourceLine.close();
					} else {
						errored = true;
					}
				}
			}
		}
	}

	/**
	 * Stops the AudioPlayer
	 */

	public void halt() {
		halted = true;
	}

	/**
	 * @return whether or not the AudioPlayer is stopped
	 */

	public boolean isHalted() {
		return halted;
	}

	/**
	 * @return whether or not audio is paused
	 */

	public boolean isPaused() {
		return paused;
	}

	/**
	 * @param paused
	 */

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * @return whether or not an error has occur
	 */

	public boolean isErrored() {
		return errored;
	}

	/**
	 * @return the sounds to play
	 */

	public File[] getSounds() {
		return sounds;
	}

	/**
	 * @return float
	 */

	public double getVol() {
		return vol;
	}

	/**
	 * @param vol
	 */

	public void setVol(double vol) {
		this.vol = vol;

		if (sourceLine != null && sourceLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			((FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN))
					.setValue((float) ExMath.map(vol, 0.0, 1.0, -30.00, 6.0206));
		}
	}
}
