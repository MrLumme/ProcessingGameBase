package lumCode.processingGameBase.resource;

import lumCode.processingGameBase.Settings;
import lumCode.processingGameBase.gameObject.Actor;
import lumCode.processingGameBase.gameObject.Tile;
import lumCode.processingGameBase.sound.Sound;
import lumCode.processingGameBase.sprite.Background;
import lumCode.processingGameBase.sprite.Sprite;
import processing.core.PFont;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ResourceCatalog {
	static Map<Integer, Resource> resourceMap = new HashMap<>();

	private static Map<Integer, Sprite> spriteMap = new HashMap<>();
	private static Map<Integer, Background> backgroundMap = new HashMap<>();
	private static Map<Integer, Sound> soundMap = new HashMap<>();
	private static Map<Integer, Tile> tileMap = new HashMap<>();
	private static Map<Integer, Actor> actorMap = new HashMap<>();
	private static Map<Integer, Font> fontMap = new HashMap<>();

	// -------
	// LOADING
	// -------

	public static void load() {
		autoload();
		ResourceCatalog.addResource(new Font(new File(Settings.FONT_PATH + "default.vlw")));
	}

	private static void autoload() {
		for (File background : new File(Settings.BACKGROUND_PATH).listFiles()) {
			ResourceCatalog.addResource(new Background(background));
		}
	}

	public static boolean allReady() {
		return ResourceCatalog.resourceMap.values().stream().allMatch(Resource::isReady);
	}


	// -------
	// FINDING
	// -------

	public static Resource findResource(int id) {
		return resourceMap.get(id);
	}
	public static Font findFont(int id) {
		return fontMap.get(id);
	}
	public static Sprite findSprite(int id) {
		return spriteMap.get(id);
	}
	public static Background findBackground(int id) {
		return backgroundMap.get(id);
	}
	public static Sound findSound(int id) {
		return soundMap.get(id);
	}
	public static Tile findTile(int id) {
		return tileMap.get(id);
	}
	public static Actor findActor(int id) {
		return actorMap.get(id);
	}

	// ------
	// ADDING
	// ------

	public static boolean addResource(Resource resource) {
		if (resource instanceof Font) {
			fontMap.put(resource.id, (Font) resource);
		}
		else if (resource instanceof Sprite) {
			spriteMap.put(resource.id, (Sprite) resource);
		}
		else if (resource instanceof Background) {
			backgroundMap.put(resource.id, (Background) resource);
		}
		else if (resource instanceof Sound) {
			soundMap.put(resource.id, (Sound) resource);
		}
		else if (resource instanceof Tile) {
			tileMap.put(resource.id, (Tile) resource);
		}
		else if (resource instanceof Actor) {
			actorMap.put(resource.id, (Actor) resource);
		}
		else {
			return false;
		}
		resourceMap.put(resource.id, resource);
		return true;
	}
}
