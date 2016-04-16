package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.graphics.AnimatedSprite;
import uk.co.newagedev.jnade.map.Map;
import uk.co.newagedev.jnade.map.MapItem;
import uk.co.newagedev.jnade.util.Location;

public class MapLevel extends Map {

	private Color bgColour;
	protected Player player;
	private MapItem[] clouds = new MapItem[] { new MapItem("white-cloud", new Location(-500, 100)), new MapItem("white-cloud", new Location(140, 100)) };

	public MapLevel(Player player, Color colour) {
		bgColour = colour;
		this.player = player;
	}

	public void init() {
		registerObject(new MapItem("sea", new Location(0, 370)));

		AnimatedSprite sea = (AnimatedSprite) Main.RENDERABLE_REGISTRY.getRenderable("sea");
		sea.start();

		registerObject(clouds[0]);
		registerObject(clouds[1]);
	}

	@Override
	public void render() {
		Color temp = new Color(bgColour.getRGB());
		for (int i = 14; i >= 0; i--) {
			Main.screen.renderRect(0f, Main.screen.getHeight() / 15f * i, Main.screen.getWidth(), (int) (Main.screen.getHeight() / 15f), temp);
			temp = new Color(temp.getRed() - 8, temp.getGreen() - 8, temp.getBlue() - 8);
		}

		Color ground = new Color(0xC18833);
		for (int i = 0; i < 5; i++) {
			Main.screen.renderRect(0f, Main.screen.getHeight() - 50 + (i * 10f), Main.screen.getWidth(), 10, ground);
			ground = new Color(ground.getRed() - 10, ground.getGreen() - 10, ground.getBlue());
		}

		super.render();
	}

	private int count = 0;

	public void update() {
		super.update();
		if (count % 4 == 0) {
			clouds[0].getLocation().x -= 1;
			if (clouds[0].getLocation().x <= -640) clouds[0].getLocation().x = 0;
			clouds[1].getLocation().x -= 1;
			if (clouds[1].getLocation().x <= 0) clouds[1].getLocation().x = 640;
		}
		count++;
	}
}
