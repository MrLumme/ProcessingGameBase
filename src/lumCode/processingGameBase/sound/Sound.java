package lumCode.processingGameBase.sound;

import java.io.File;

public abstract class Sound {
	private boolean playing;

	public Sound() {
		setPlaying(false);
	}

	public abstract File[] getPlaySetup();

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
}
