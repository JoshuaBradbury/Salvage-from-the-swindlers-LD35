package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.map.MapCustom;
import uk.co.newagedev.jnade.util.Location;

public class Player extends MapCustom {

	public Player(Location loc) {
		super(loc);
	}

	@Override
	public void render() {
		Main.screen.renderRect(this.getLocation().x, this.getLocation().y, 64, 64, Color.WHITE);
	}
}
