package lumCode.processingGameBase.resource;

import lumCode.processingGameBase.Main;
import lumCode.processingGameBase.Settings;
import processing.core.PApplet;
import processing.core.PFont;

import java.io.File;

public class Font extends Resource {
	private PApplet p;
	public final PFont font;

	// -----------
	// CONSTRUCTOR
	// -----------

	public Font(File font) {
		this(font, 32, true);
	}

	public Font(File font, float size, boolean smooth) {
		super();
		p = Main.instance();
		if (font.getAbsolutePath().endsWith(".vlw")) {
			this.font = p.loadFont(font.getAbsolutePath());
		} else {
			this.font = p.createFont(font.getAbsolutePath(), size, smooth);
		}
	}

	// --------
	// INTERNAL
	// --------

	@Override
	protected boolean isReady() {
		return false;
	}
}
