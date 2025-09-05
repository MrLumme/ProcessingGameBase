package lumCode.processingGameBase.gameObject;

import lumCode.processingGameBase.sprite.Sprite;

public class Actor extends ObjectResource {

	// -----------
	// CONSTRUCTOR
	// -----------

	public Actor(float x, float y) {
		super(x, y);
	}

	public Actor(float x, float y, Sprite sprite) {
		super(x, y, sprite);
	}

	// -------
	// UTILITY
	// -------
}
