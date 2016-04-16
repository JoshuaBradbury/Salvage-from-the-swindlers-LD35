package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;

import uk.co.newagedev.jnade.Game;
import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.input.KeyBinding;
import uk.co.newagedev.jnade.map.Map;
import uk.co.newagedev.jnade.openglgraphics.AnimatedSprite;
import uk.co.newagedev.jnade.openglgraphics.SpriteRegistry;
import uk.co.newagedev.jnade.util.Vector2f;

public class GameMain implements Game {

	public static Main main;
	public Map currentMap;
	private int currentMapIndex;

	public static void main(String[] args) {
		main = new Main();

		main.setScreenSize(640, 480);
		main.setTitle("Salvage from the swindlers! LD35");

		main.setBackgroundColour(Color.BLACK);

		GameMain game = new GameMain();

		main.setGame(game);

		main.start();
	}
	
	public void swapToMap(int i) {
		currentMapIndex = i;
		//AudioClip clip = Main.AUDIO_REGISTRY.getAudioClip("bg-level");
		switch (i) {
		case -2:
			currentMap = new MainMenu();
			break;
		case -1:
			currentMap = new MapOverview();
			//if (clip.isPlaying()) clip.stop();
			break;
		case 0:
			currentMap = new MapFirstLevel();
			//if (!clip.isPlaying()) clip.playLoop();
			break;
		}
		currentMap.setGame(this);
		currentMap.init();
	}

	public void init() {
		KeyBinding.bindKey("exit", KeyBinding.KEY_ESCAPE);
		
		KeyBinding.bindKey("playerLeft", KeyBinding.KEY_LEFT);
		KeyBinding.bindKey("playerRight", KeyBinding.KEY_RIGHT);
		KeyBinding.bindKey("playerClimbUp", KeyBinding.KEY_UP);
		KeyBinding.bindKey("playerClimbDown", KeyBinding.KEY_DOWN);
		KeyBinding.bindKey("playerAction", KeyBinding.KEY_ENTER);
		KeyBinding.bindKey("playerTransform", KeyBinding.KEY_LEFT_CONTROL);
		
		//Main.AUDIO_REGISTRY.registerAudioClip("bg-level", new File("audio/bg-level.wav"));
		
		SpriteRegistry.registerSprite("sea", new AnimatedSprite("res/images/sea/", "sea", 12));
		SpriteRegistry.registerSprite("rough-sea", new AnimatedSprite("res/images/sea/", "rough-sea", 3));
		SpriteRegistry.registerSprite("white-cloud", Main.getScreen().loadImageFromFile("res/images/clouds.png"));
		SpriteRegistry.registerSprite("dark-cloud", Main.getScreen().loadImageFromFile("res/images/dark clouds.png"));
		
		SpriteRegistry.registerSprite("palm-tree", Main.getScreen().loadImageFromFile("res/images/palmTree.png"));
		
		swapToMap(0);
	}

	public void update() {
		currentMap.update();
		if (KeyBinding.isBindingReleasing("exit")) {
			if (currentMapIndex == -1) {
				main.stop();
			} else {
				swapToMap(-1);
			}
		}
	}

	public void render() {
		currentMap.render();
		Main.getScreen().renderSpriteIgnoringCamera("playerNormalWalk3", new Vector2f(100, 0));
		Main.getScreen().renderSpriteIgnoringCamera("playerNormalWalk2", new Vector2f(200, 0));
		Main.getScreen().renderSpriteIgnoringCamera("playerNormalWalk1", new Vector2f(300, 0));
		Main.getScreen().renderSpriteIgnoringCamera("playerNormalWalk0", new Vector2f(400, 0));
	}
}
