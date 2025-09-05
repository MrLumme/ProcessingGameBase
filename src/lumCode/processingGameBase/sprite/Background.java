package lumCode.processingGameBase.sprite;

import lumCode.processingGameBase.Main;
import lumCode.processingGameBase.resource.Resource;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class Background extends ImageResource {
	public int offsetX, offsetY;
	public ImageDisplayMode displayMode;

	// -----------
	// CONSTRUCTOR
	// -----------

	public Background(File image) {
		super(image);
	}

	// ----
	// DRAW
	// ----

	@Override
	public void draw() {
		switch (displayMode) {
			case TILE:
				for (int x = (image.width % offsetX) - image.width; x < p.width; x+= image.width) {
					for (int y = (image.height % offsetY) - image.height; y < p.height; y+= image.height) {
						p.image(image, x, y);
					}
				}
				break;
			case SCALE:
				if (p.width <= p.height) {
					p.image(image, offsetX, offsetY, p.width, p.height / p.width * image.height);
				} else {
					p.image(image, offsetX, offsetY, p.width / p.height * image.width, p.height);
				}
				break;
			case STRETCH:
				p.image(image, 0, 0, p.width, p.height);
				break;
		}
	}
}
