package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;

import uk.co.newagedev.jnade.map.MapItem;
import uk.co.newagedev.jnade.util.Location;

public class MapFirstLevel extends MapLevel {

	public MapFirstLevel() {
		super(new Player(new Location(10, 410)), new Color(0xCEE6EF));
	}
	
	public void init() {
		super.init();
		
		registerObject(new MapItem("palm-tree", new Location(400, 280)));
		
		registerObject(player);
	}
}
