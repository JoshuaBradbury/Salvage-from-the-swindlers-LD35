package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;
import java.awt.Rectangle;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.map.Map;
import uk.co.newagedev.jnade.map.MapItem;
import uk.co.newagedev.jnade.openglgraphics.AnimatedSprite;
import uk.co.newagedev.jnade.openglgraphics.SpriteRegistry;
import uk.co.newagedev.jnade.util.Colour;
import uk.co.newagedev.jnade.util.Vector2f;

public class MapLevel extends Map {

	private Color bgColour;
	protected Player player;
	private MapItem[] clouds = new MapItem[] { new MapItem("white-cloud", new Vector2f(-500, 100)), new MapItem("white-cloud", new Vector2f(140, 100)) };
	protected MapItem[][] sectionsOfLevel;
	private int currentSection = 0;

	public MapLevel(Color colour, int sectionNum) {
		bgColour = colour;
		sectionsOfLevel = new MapItem[sectionNum][20];
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void init() {
		registerObject(new MapItem("sea", new Vector2f(0, 370)));

		AnimatedSprite sea = (AnimatedSprite) SpriteRegistry.getSprite("sea");
		sea.start();

		registerObject(clouds[0]);
		registerObject(clouds[1]);
	}

	public boolean isFirstSection() {
		return currentSection == 0;
	}

	public boolean isLastSection() {
		return currentSection == sectionsOfLevel.length - 1;
	}

	public void nextSection() {
		selectSection(currentSection + 1);
		currentSection++;
	}

	public void prevSection() {
		selectSection(currentSection - 1);
		currentSection--;
	}

	public void registerSections() {
		for (int i = 0; i < sectionsOfLevel.length; i++) {
			for (int j = 0; j < sectionsOfLevel[i].length; j++) {
				if (sectionsOfLevel[i][j] != null) {
					sectionsOfLevel[i][j].hide();
					registerObject(sectionsOfLevel[i][j]);
				}
			}
		}
	}

	public void selectSection(int section) {
		for (int i = 0; i < sectionsOfLevel[currentSection].length; i++) {
			if (sectionsOfLevel[currentSection][i] != null)
				sectionsOfLevel[currentSection][i].hide();
		}
		for (int i = 0; i < sectionsOfLevel[section].length; i++) {
			if (sectionsOfLevel[section][i] != null)
				sectionsOfLevel[section][i].show();
		}
	}

	@Override
	public void render() {
		Color temp = new Color(bgColour.getRGB());
		for (int i = 14; i >= 0; i--) {
			Main.getScreen().renderQuad(new Rectangle(0, (int) (Main.getHeight() / 15f * i), Main.getWidth(), (int) (Main.getHeight() / 15f)), Colour.getFloatsFromColor(temp));
			temp = new Color(temp.getRed() - 8, temp.getGreen() - 8, temp.getBlue() - 8);
		}

		Color ground = new Color(0xC18833);
		for (int i = 0; i < 5; i++) {
			Main.getScreen().renderQuad(new Rectangle(0, (int) (Main.getHeight() - 50 + (i * 10f)), Main.getWidth(), 10), Colour.getFloatsFromColor(ground));
			ground = new Color(ground.getRed() - 10, ground.getGreen() - 10, ground.getBlue());
		}

		super.render();
	}

	private int count = 0;

	public void update() {
		super.update();
		if (count % 4 == 0) {
			clouds[0].getLocation().x -= 1;
			if (clouds[0].getLocation().x <= -640)
				clouds[0].getLocation().x = 0;
			clouds[1].getLocation().x -= 1;
			if (clouds[1].getLocation().x <= 0)
				clouds[1].getLocation().x = 640;
		}
		count++;
	}
}
