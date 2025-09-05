package lumCode.processingGameBase.sprite;

import lumCode.processingGameBase.Main;
import lumCode.processingGameBase.resource.Resource;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class Sprite extends ImageResource {
	public float w, h;

	// -----------
	// CONSTRUCTOR
	// -----------

	public Sprite(File image) {
		super(image);
	}

	public Sprite(float w, float h, File image) {
		this(image);
		this.w = w;
		this.h = h;
	}

	// ----
	// DRAW
	// ----

	@Override
	public void draw(float x, float y) {
		if (w > 0 && h > 0) {
			p.image(image, x, y, w, h);
		} else {
			p.image(image, x, y);
		}
	}
}
