package uk.co.newagedev.salvagefromtheswindler;

public enum ShapeShift {
	NORMAL,
	SQUIRREL,
	ANGEL,
	MOLE,
	ALLIGATOR;
	
	public String niceName() {
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
	
	public ShapeShift nextShape() {
		ShapeShift next = null;
		switch (this) {
		case ALLIGATOR:
			next = ANGEL;
			break;
		case ANGEL:
			next = NORMAL;
			break;
		case NORMAL:
			next = MOLE;
			break;
		case MOLE:
			next = SQUIRREL;
			break;
		case SQUIRREL:
			next = ALLIGATOR;
			break;
		default:
			break;
		}
		return next;
	}
}
