public class Sprite {
	private static int idCounter = 0;
	
	private PApplet p;

	public final int id;
  public float w, h;
  public PImage image;

  // -----------
  // CONSTRUCTOR
  // -----------

	private Sprite() {
		id = idCounter;
		idCounter++;
		p = Main.instance()
	}

	public Sprite(PImage image) {
		this();
		this.image = image;
	}

	public Sprite(float w, float h, PImage image) {
		this(image);
    this.w = w;
    this.h = h;
	}

	public Sprite(File image) {
		this();
		this.image = p.loadImage(image);
	}

	public Sprite(float w, float h, File image) {
		this(image);
    this.w = w;
    this.h = h;
	}

  // ----
  // DRAW
  // ----
  
  public void drawAt(float x, float y) {
    if (w > 0 && h > 0) {
      p.image(image, x, y, w, h)
    } else {
      p.image(image, x, y)
    }
  }

  // --------
  // INTERNAL
  // --------

  boolean isReady() {
  	if (image != null && image.width != -1) {
      return true;
    }
    return false;
  }
}
