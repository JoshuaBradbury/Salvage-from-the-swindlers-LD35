package uk.co.newagedev.salvagefromtheswindler;

import uk.co.newagedev.jnade.Task;
import uk.co.newagedev.jnade.TaskScheduler;
import uk.co.newagedev.jnade.input.KeyBinding;
import uk.co.newagedev.jnade.map.Map;
import uk.co.newagedev.jnade.util.Vector2f;

public class MapOverview extends Map {
	
	private Vector2f[] mapPoints = new Vector2f[] {new Vector2f(100, 100), new Vector2f(200, 200), new Vector2f(300, 300), new Vector2f(400, 400), new Vector2f(250, 360)};
	private Player player = new Player(mapPoints[0].clone(), null);
	private boolean notMoving = true;
	
	public void movePlayerLeft() {
		int loc = getPlayerLoc();
		if (loc >= 0) {
			loc -= 1;
			if (loc == -1)
				loc += mapPoints.length;
		}
		movePlayerToLocation(mapPoints[loc]);
	}
	
	public void movePlayerRight() {
		int loc = getPlayerLoc();
		if (loc >= 0) {
			loc = (loc + 1) % mapPoints.length;
		}
		movePlayerToLocation(mapPoints[loc]);
	}
	
	public void movePlayerToLocation(Vector2f loc) {
		if (notMoving) {
			Vector2f diff = new Vector2f(loc.x - player.getLocation().x, loc.y - player.getLocation().y);
			TaskScheduler.runTask(new Task(1) {
				private int count = 0;
				
				public boolean shouldRepeat() {
					return !notMoving;
				}
				
				public void run() {
					count++;
					notMoving = false;
					if (count == 20) {
						player.getLocation().x = loc.x;
						player.getLocation().y = loc.y;
						notMoving = true;
					} else {
						player.getLocation().x += diff.x / 20f;
						player.getLocation().y += diff.y / 20f;
					}
				}
			});
		}
	}
	
	public int getPlayerLoc() {
		for (int i = 0; i < mapPoints.length; i++) {
			if (mapPoints[i].equals(player.getLocation()))
				return i;
		}
		return -1;
	}
	
	public void update() {
		if (KeyBinding.isBindingReleasing("playerLeft")) {
			if (notMoving) {
				movePlayerLeft();
			}
		} else if (KeyBinding.isBindingReleasing("playerRight")) {
			if (notMoving) {
				movePlayerRight();
			}
		} else if (KeyBinding.isBindingReleasing("playerAction")) {
			((GameMain) getGame()).swapToMap(getPlayerLoc());
		}
	}
	
	public void init() {
		registerObject(player);
	}
}
