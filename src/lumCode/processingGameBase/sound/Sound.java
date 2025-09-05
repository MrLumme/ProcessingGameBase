package lumCode.processingGameBase.sound;

import lumCode.processingGameBase.resource.Resource;

import java.io.File;

public abstract class Sound extends Resource {
	private boolean playing;

	// -----------
	// CONSTRUCTOR
	// -----------

	public Sound() {
		super();
	}

	// --------
	// UTIILITY
	// --------

	public abstract File[] getPlaySetup();

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	// --------
	// INTERNAL
	// --------

	@Override
	protected boolean isReady() {
		return true;
	}
}
