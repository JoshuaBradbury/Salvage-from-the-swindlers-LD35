package uk.co.newagedev.salvagefromtheswindler;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.graphics.AnimatedSprite;
import uk.co.newagedev.jnade.input.KeyBinding;
import uk.co.newagedev.jnade.map.MapCustom;
import uk.co.newagedev.jnade.util.Location;

public class Player extends MapCustom {

	private boolean level, shapeShifting;
	private ShapeShift shape = ShapeShift.NORMAL;
	private int walking = 0, climbing = 0;

	private AnimatedSprite normalWalk = new AnimatedSprite("res/images/normal.png", 16, 2, 0, 3);
	private AnimatedSprite moleWalk = new AnimatedSprite("res/images/mole.png", 16, 2, 0, 3);
	private AnimatedSprite alligatorWalk = new AnimatedSprite("res/images/alligator.png", 16, 2, 0, 3);
	private AnimatedSprite angelWalk = new AnimatedSprite("res/images/angel.png", 16, 2, 0, 3);
	private AnimatedSprite squirrelWalk = new AnimatedSprite("res/images/squirrel.png", 16, 2, 0, 3);

	private AnimatedSprite normalShapeShift = new AnimatedSprite("res/images/normal.png", 16, 2, 4, 19);
	private AnimatedSprite moleShapeShift = new AnimatedSprite("res/images/mole.png", 16, 2, 4, 19);
	private AnimatedSprite alligatorShapeShift = new AnimatedSprite("res/images/alligator.png", 16, 2, 4, 19);
	private AnimatedSprite angelShapeShift = new AnimatedSprite("res/images/angel.png", 16, 2, 4, 19);
	private AnimatedSprite squirrelShapeShift = new AnimatedSprite("res/images/squirrel.png", 16, 2, 4, 19);

	public Player(Location loc, boolean level) {
		super(loc);
		this.level = level;
	}

	public Player(Location loc) {
		this(loc, true);
	}

	public void setWalkingDirection(int dir) {
		walking = dir;
	}

	private int count = 0;

	public void update() {
		if (walking == -1) {
			count++;
			if (count % 10 == 0) {
				count -= 10;
				switch (shape) {
				case ALLIGATOR:
					alligatorWalk.setFlipX(true);
					alligatorWalk.nextFrame();
					break;
				case ANGEL:
					angelWalk.setFlipX(true);
					angelWalk.nextFrame();
					break;
				case MOLE:
					moleWalk.setFlipX(true);
					moleWalk.nextFrame();
					break;
				case NORMAL:
					normalWalk.setFlipX(true);
					normalWalk.nextFrame();
					break;
				case SQUIRREL:
					squirrelWalk.setFlipX(true);
					squirrelWalk.nextFrame();
					break;
				default:
					break;
				}
			}
		} else if (walking == 1) {
			count++;
			if (count % 10 == 0) {
				count -= 10;
				switch (shape) {
				case ALLIGATOR:
					alligatorWalk.setFlipX(false);
					alligatorWalk.nextFrame();
					break;
				case ANGEL:
					angelWalk.setFlipX(false);
					angelWalk.nextFrame();
					break;
				case MOLE:
					moleWalk.setFlipX(false);
					moleWalk.nextFrame();
					break;
				case NORMAL:
					normalWalk.setFlipX(false);
					normalWalk.nextFrame();
					break;
				case SQUIRREL:
					squirrelWalk.setFlipX(false);
					squirrelWalk.nextFrame();
				default:
					break;
				}
			}
		} else {
			normalWalk.setFrame(0);
		}
		if (level) {
			if (!shapeShifting && KeyBinding.isKeyReleasing("playerTransform")) {
				count = 0;
				shapeShifting = true;
				walking = 0;
				if (shape == ShapeShift.ALLIGATOR)
					alligatorShapeShift.setFrame(0);
				if (shape == ShapeShift.ANGEL)
					angelShapeShift.setFrame(0);
				if (shape == ShapeShift.MOLE)
					moleShapeShift.setFrame(0);
				if (shape == ShapeShift.NORMAL)
					normalShapeShift.setFrame(0);
				if (shape == ShapeShift.SQUIRREL)
					squirrelShapeShift.setFrame(0);
			} else if (shapeShifting) {
				count++;
				if (count < 16) {
					switch (shape) {
					case ALLIGATOR:
						alligatorShapeShift.nextFrame();
						break;
					case ANGEL:
						angelShapeShift.nextFrame();
						break;
					case MOLE:
						moleShapeShift.nextFrame();
						break;
					case NORMAL:
						normalShapeShift.nextFrame();
						break;
					case SQUIRREL:
						squirrelShapeShift.nextFrame();
						break;
					default:
						break;
					}
				} else if (count == 16) {
					shape = shape.nextShape();
					if (shape == ShapeShift.ALLIGATOR)
						alligatorShapeShift.setFrame(15);
					if (shape == ShapeShift.ANGEL)
						angelShapeShift.setFrame(15);
					if (shape == ShapeShift.MOLE)
						moleShapeShift.setFrame(15);
					if (shape == ShapeShift.NORMAL)
						normalShapeShift.setFrame(15);
					if (shape == ShapeShift.SQUIRREL)
						squirrelShapeShift.setFrame(15);
				} else if (count < 32) {
					switch (shape) {
					case ALLIGATOR:
						alligatorShapeShift.prevFrame();
						break;
					case ANGEL:
						angelShapeShift.prevFrame();
						break;
					case MOLE:
						moleShapeShift.prevFrame();
						break;
					case NORMAL:
						normalShapeShift.prevFrame();
						break;
					case SQUIRREL:
						squirrelShapeShift.prevFrame();
						break;
					default:
						break;
					}
				} else {
					shapeShifting = false;
					count = 0;
				}
			} else {
				boolean left = KeyBinding.isKeyDown("playerLeft"), right = KeyBinding.isKeyDown("playerRight");
				if (left && !right) {
					getLocation().x -= 1;
					walking = -1;
				} else if (right && !left) {
					getLocation().x += 1;
					walking = 1;
				} else {
					walking = 0;
				}
			}
		}
	}

	public int[] getCurrentPixels() {
		if (shapeShifting) {
			switch (shape) {
			case ALLIGATOR:
				return alligatorShapeShift.getPixels();
			case ANGEL:
				return angelShapeShift.getPixels();
			case MOLE:
				return moleShapeShift.getPixels();
			case NORMAL:
				return normalShapeShift.getPixels();
			case SQUIRREL:
				return squirrelShapeShift.getPixels();
			default:
				break;
			}
		} else {
			switch (shape) {
			case ALLIGATOR:
				return alligatorWalk.getPixels();
			case ANGEL:
				return angelWalk.getPixels();
			case MOLE:
				return moleWalk.getPixels();
			case NORMAL:
				return normalWalk.getPixels();
			case SQUIRREL:
				return squirrelWalk.getPixels();
			default:
				break;
			}
		}
		return new int[0];
	}

	@Override
	public void render() {
		Main.screen.renderImage(getLocation().x, getLocation().y, 32, 32, getCurrentPixels());
	}
}
