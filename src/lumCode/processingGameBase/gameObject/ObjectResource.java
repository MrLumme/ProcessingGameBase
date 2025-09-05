package lumCode.processingGameBase.gameObject;

import lumCode.processingGameBase.resource.Resource;
import lumCode.processingGameBase.sprite.Sprite;

public abstract class ObjectResource extends Resource {
	public float x, y;
	public int depth;
	public Sprite sprite;

	// -----------
	// CONSTRUCTOR
	// -----------

	protected ObjectResource(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	protected ObjectResource(float x, float y, Sprite sprite) {
		this(x, y);
		this.sprite = sprite;
	}

	// ----
	// DRAW
	// ----

	public void draw() {
		sprite.draw(x, y);
	}

	// --------
	// INTERNAL
	// --------

	@Override
	protected boolean isReady() {
		return true;
	}
}
