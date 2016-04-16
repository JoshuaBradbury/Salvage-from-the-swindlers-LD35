package uk.co.newagedev.salvagefromtheswindler;

import java.awt.Rectangle;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.map.MapCustom;
import uk.co.newagedev.jnade.util.Colour;
import uk.co.newagedev.jnade.util.Vector2f;

public class PopupMessage extends MapCustom {

	private String text;
	private Rectangle rect;
	
	public PopupMessage(String text, Rectangle rect, Vector2f location) {
		super(location);
		this.text = text;
		this.rect = rect;
	}

	@Override
	public void render() {
		if (rect == null) {
			Main.getScreen().renderQuad(new Rectangle(0, 0, Main.getWidth(), 50), Colour.BLACK);
		} else {
			Main.getScreen().renderQuad(rect, Colour.BLACK);
		}
	}

}
