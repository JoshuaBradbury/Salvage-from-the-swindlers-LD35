package uk.co.newagedev.salvagefromtheswindler;

import uk.co.newagedev.jnade.Main;
import uk.co.newagedev.jnade.input.KeyBinding;
import uk.co.newagedev.jnade.map.MapCustom;
import uk.co.newagedev.jnade.openglgraphics.AnimatedSprite;
import uk.co.newagedev.jnade.openglgraphics.Sprite;
import uk.co.newagedev.jnade.util.Vector2f;

public class Player extends MapCustom {

	private boolean shapeShifting;
	private ShapeShift shape = ShapeShift.NORMAL;
	private int walking = 0, climbing = 0;
	private MapLevel level;

	private AnimatedSprite normalWalk = new AnimatedSprite("res/images/normal/", "playerNormalWalk", 10, 0, 3);
	private AnimatedSprite moleWalk = new AnimatedSprite("res/images/mole/", "playerMoleWalk", 0, 3);
	private AnimatedSprite alligatorWalk = new AnimatedSprite("res/images/alligator/", "playerAlligatorWalk", 0, 3);
	private AnimatedSprite angelWalk = new AnimatedSprite("res/images/angel/", "playerAngelWalk", 0, 3);
	private AnimatedSprite squirrelWalk = new AnimatedSprite("res/images/squirrel/", "playerSquirrelWalk", 0, 3);

	private AnimatedSprite normalShapeShift = new AnimatedSprite("res/images/normal/", "playerNormalTransfom", 4, 19);
	private AnimatedSprite moleShapeShift = new AnimatedSprite("res/images/mole/", "playerMoleTransfom", 4, 19);
	private AnimatedSprite alligatorShapeShift = new AnimatedSprite("res/images/alligator/", "playerAlligatorTransfom", 4, 19);
	private AnimatedSprite angelShapeShift = new AnimatedSprite("res/images/angel/", "playerAngelTransfom", 4, 19);
	private AnimatedSprite squirrelShapeShift = new AnimatedSprite("res/images/squirrel/", "playerSquirrelTransfom", 4, 19);

	public Player(Vector2f loc, MapLevel level) {
		super(loc);
		this.level = level;
		normalWalk.start();
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
					//normalWalk.nextFrame();
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
					//normalWalk.nextFrame();
					break;
				case SQUIRREL:
					squirrelWalk.setFlipX(false);
					squirrelWalk.nextFrame();
				default:
					break;
				}
			}
		}
		if (level != null) {
			if (climbing == 0 && KeyBinding.isBindingDown("playerClimbUp")) {
				climbing = 1;
			} else if (climbing == -1 && KeyBinding.isBindingDown("playerClimbDown")) {
				
			}
			
			if (!shapeShifting && KeyBinding.isBindingReleasing("playerTransform")) {
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
				boolean left = KeyBinding.isBindingDown("playerLeft"), right = KeyBinding.isBindingDown("playerRight");
				if (left && !right) {
					getLocation().x -= 1;
					walking = -1;

					if (getLocation().x < -16) {
						if (!level.isFirstSection()) {
							level.prevSection();
							getLocation().x = 624;
						} else {
							getLocation().x += 1;
						}
					}
				} else if (right && !left) {
					getLocation().x += 1;
					walking = 1;

					if (getLocation().x > 624) {
						if (!level.isLastSection()) {
							level.nextSection();
							getLocation().x = -16;
						} else {
							getLocation().x -= 1;
						}
					}
				} else {
					walking = 0;
				}
			}
		}
	}

	public Sprite getCurrentSprite() {
		if (shapeShifting) {
			switch (shape) {
			case ALLIGATOR:
				return alligatorShapeShift.getSprite();
			case ANGEL:
				return angelShapeShift.getSprite();
			case MOLE:
				return moleShapeShift.getSprite();
			case NORMAL:
				return normalShapeShift.getSprite();
			case SQUIRREL:
				return squirrelShapeShift.getSprite();
			default:
				break;
			}
		} else {
			switch (shape) {
			case ALLIGATOR:
				return alligatorWalk.getSprite();
			case ANGEL:
				return angelWalk.getSprite();
			case MOLE:
				return moleWalk.getSprite();
			case NORMAL:
				return normalWalk.getSprite();
			case SQUIRREL:
				return squirrelWalk.getSprite();
			default:
				break;
			}
		}
		return null;
	}

	@Override
	public void render() {
		Main.getScreen().renderSpriteIgnoringCamera(getCurrentSprite().getName(), getLocation());
	}
}
