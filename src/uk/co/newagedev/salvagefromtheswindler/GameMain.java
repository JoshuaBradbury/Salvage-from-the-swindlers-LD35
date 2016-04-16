package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;

import uk.co.newagedev.jnade.Game;
import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.audio.AudioClip;
import uk.co.newagedev.jnade.input.KeyBinding;
import uk.co.newagedev.jnade.map.Map;

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
		AudioClip clip = Main.AUDIO_REGISTRY.getAudioClip("bg-level");
		switch (i) {
		case -1:
			currentMap = new MapOverview();
			if (clip.isPlaying()) clip.stop();
			break;
		case 0:
			currentMap = new MapFirstLevel();
			if (!clip.isPlaying()) clip.playLoop();
			break;
		}
		currentMap.setGame(this);
		currentMap.init();
	}

	public void init() {
		KeyBinding.bindKey("exit", KeyEvent.VK_ESCAPE);
		
		KeyBinding.bindKey("playerLeft", KeyEvent.VK_LEFT);
		KeyBinding.bindKey("playerRight", KeyEvent.VK_RIGHT);
		KeyBinding.bindKey("playerJump", KeyEvent.VK_SPACE);
		KeyBinding.bindKey("playerClimbUp", KeyEvent.VK_UP);
		KeyBinding.bindKey("playerClimbDown", KeyEvent.VK_DOWN);
		KeyBinding.bindKey("playerAction", KeyEvent.VK_ENTER);
		
		Main.AUDIO_REGISTRY.registerAudioClip("bg-level", new File("audio/bg-level.wav"));
		
		swapToMap(-1);
	}

	public void update() {
		currentMap.update();
		if (KeyBinding.isKeyReleasing("exit")) {
			if (currentMapIndex == -1) {
				main.stop();
			} else {
				swapToMap(-1);
			}
		}
	}

	public void render() {
		currentMap.render();
	}
}
