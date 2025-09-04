public class GameObject {
	private static int idCounter = 0;
	
	private PApplet p;
	
	public final int id;
	public int depth;
	public float x, y;
	public Sprite sprite;

  public GameObject() {
		id = idCounter;
		idCounter++;
		p = Main.instance()
  }

	public GameObject(float x, float y) {
		this();
		this.x = x;
		this.y = y;
	}

	public GameObject(float x, float y, Sprite sprite) {
		this(x, y);
		this.sprite = sprite;
	}

	public void draw() {
		sprite.drawAt(x, y);
	}
}
