package lumCode.processingGameBase.gameObject;

import lumCode.processingGameBase.sprite.Sprite;

public abstract class GameObject {
	public float x, y;
	public Sprite sprite;

	protected GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}

	protected GameObject(float x, float y, Sprite sprite) {
		this(x, y);
		this.sprite = sprite;
	}

	public void draw() {
		sprite.drawAt(x, y);
	}
}
