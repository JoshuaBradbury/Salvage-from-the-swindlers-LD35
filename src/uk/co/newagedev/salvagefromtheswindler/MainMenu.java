package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.map.Map;
import uk.co.newagedev.jnade.map.MapItem;
import uk.co.newagedev.jnade.openglgraphics.AnimatedSprite;
import uk.co.newagedev.jnade.openglgraphics.SpriteRegistry;
import uk.co.newagedev.jnade.util.Colour;
import uk.co.newagedev.jnade.util.Vector2f;

public class MainMenu extends Map {

	private AnimatedSprite rain = new AnimatedSprite("res/images/rain/", "rain", 4);
	private MapItem[] clouds = new MapItem[] { new MapItem("dark-cloud", new Vector2f(-500, 0)), new MapItem("dark-cloud", new Vector2f(140, 0)) };

	private int lightning = -1;
	private Random rand = new Random();

	@Override
	public void init() {
		registerObject(new MapItem("rough-sea", new Vector2f(0, 420)));

		AnimatedSprite sea = (AnimatedSprite) SpriteRegistry.getSprite("rough-sea");
		sea.start();

		registerObject(clouds[0]);
		registerObject(clouds[1]);

		rain.start();
		SpriteRegistry.registerSprite("rain", rain);

		registerObject(new MapItem("rain", new Vector2f(0, 0)));
	}

	public void render() {
		Color temp = new Color(0x9AA2AB);
		for (int i = 14; i >= 0; i--) {
			Main.getScreen().renderQuad(new Rectangle(0, (int) (Main.getHeight() / 15f * i), Main.getWidth(), (int) (Main.getHeight() / 15f)), Colour.getFloatsFromColor(temp));
			temp = new Color(temp.getRed() - 8, temp.getGreen() - 8, temp.getBlue() - 8);
		}

		super.render();

		if (lightning >= 0) {
			float n = lightning * 2.5f / 255.0f;
			Main.getScreen().renderQuad(new Rectangle(0, 0, Main.getWidth(), Main.getHeight()), new float[] { n, n, n, n });

			lightning--;
		}
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

		if (lightning < 0) {
			if (rand.nextInt(10000) < 50) {
				lightning = 100;
			}
		}
	}
}
