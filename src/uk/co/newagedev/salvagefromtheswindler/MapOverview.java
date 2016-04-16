package uk.co.newagedev.salvagefromtheswindler;

import uk.co.newagedev.jnade.Task;
import uk.co.newagedev.jnade.TaskScheduler;
import uk.co.newagedev.jnade.input.KeyBinding;
import uk.co.newagedev.jnade.map.Map;
import uk.co.newagedev.jnade.util.Location;

public class MapOverview extends Map {
	
	private Location[] mapPoints = new Location[] {new Location(100, 100), new Location(200, 200), new Location(300, 300), new Location(400, 400), new Location(250, 360)};
	private Player player = new Player(mapPoints[0].clone(), false);
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
	
	public void movePlayerToLocation(Location loc) {
		if (notMoving) {
			Location diff = new Location(loc.x - player.getLocation().x, loc.y - player.getLocation().y);
			TaskScheduler.runTask(new Task(1) {
				private int count = 0;
				
				public boolean shouldRepeat() {
					return !notMoving;
				}
				
				public void run() {
					count++;
					notMoving = false;
					if (count == 20) {
						player.getLocation().moveTo(loc.x, loc.y);
						notMoving = true;
					} else {
						player.getLocation().moveBy(diff.x / 20f, diff.y / 20f);
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
		if (KeyBinding.isKeyReleasing("playerLeft")) {
			if (notMoving) {
				movePlayerLeft();
			}
		} else if (KeyBinding.isKeyReleasing("playerRight")) {
			if (notMoving) {
				movePlayerRight();
			}
		} else if (KeyBinding.isKeyReleasing("playerAction")) {
			((GameMain) getGame()).swapToMap(getPlayerLoc());
		}
	}
	
	public void init() {
		registerObject(player);
	}
}
