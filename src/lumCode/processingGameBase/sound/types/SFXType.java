package lumCode.processingGameBase.sound.types;

import java.io.File;

import lumCode.processingGameBase.Settings;

public enum SFXType {
	BUTTON;

	public static File getFile(SFXType type) {
		return new File(Settings.AUDIO_PATH + "sfx/" + type.name().toLowerCase() + ".wav");
	}
}
