package lumCode.processingGameBase.sound;

import java.io.File;

import lumCode.processingGameBase.Settings;

public enum SFXType {
	BUTTON;

	public static File getFile(SFXType type) {
		return new File(Settings.SOUND_PATH + "sfx/" + type.name().toLowerCase() + ".wav");
	}
}
