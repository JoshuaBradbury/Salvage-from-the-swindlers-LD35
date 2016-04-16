package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.map.Map;

public class MapLevel extends Map {

	private Color bgColour;
	
	public MapLevel(Color colour) {
		bgColour = colour;
	}
	
	public void init() {
		
	}
	
	@Override
	public void render() {
		Color temp = new Color(bgColour.getRGB());
		for (int i = 14; i >= 0; i--) {
			Main.screen.renderRect(0f, Main.screen.getHeight() / 15f * i, Main.screen.getWidth(), (int) (Main.screen.getHeight() / 15f), temp);
			temp = new Color(temp.getRed() - 8, temp.getGreen() - 8, temp.getBlue() - 8);
		}
		
		super.render();
	}
}
