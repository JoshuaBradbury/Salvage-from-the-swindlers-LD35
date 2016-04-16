package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Color;

import uk.co.newagedev.jnade.map.MapItem;
import uk.co.newagedev.jnade.util.Vector2f;

public class MapFirstLevel extends MapLevel {

	public MapFirstLevel() {
		super(new Color(0xCEE6EF), 3);
		setPlayer(new Player(new Vector2f(10, 410), this));
	}
	
	public void init() {
		super.init();
		
		sectionsOfLevel[0][0] = new MapItem("palm-tree", new Vector2f(400, 280));
		
		registerSections();

		registerObject(player);

		selectSection(0);
	}
}
