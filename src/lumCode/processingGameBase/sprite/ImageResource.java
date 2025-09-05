package lumCode.processingGameBase.sprite;

import lumCode.processingGameBase.Main;
import lumCode.processingGameBase.resource.Resource;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public abstract class ImageResource extends Resource {
	protected PApplet p;
	public PImage image;

	// -----------
	// CONSTRUCTOR
	// -----------

	protected ImageResource(File image) {
		super();
		p = Main.instance();
		this.image = p.loadImage(image.getAbsolutePath());
	}

	// ----
	// DRAW
	// ----

	protected void draw() {}
	protected void draw(float x, float y) {}

	// --------
	// INTERNAL
	// --------

	@Override
	protected boolean isReady() {
		if (image != null && image.width != -1) {
			return true;
		}
		return false;
	}
}
