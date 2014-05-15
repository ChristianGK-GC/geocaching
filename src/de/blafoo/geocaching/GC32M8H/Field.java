package de.blafoo.geocaching.GC32M8H;

public class Field {

	private final int y;
	private final int x;
	private int value;

	public Field(final int y, final int x, final int max) {
		/*
		if ( x < 0 || y < 0 || x >= max || y >= max ) {
			throw new IllegalArgumentException("outside");
		}
		 */

		this.y = y;
		this.x = x;
	}

	public Field(final int y, final int x, final int max, final int value) {
		/*
		if ( x < 0 || y < 0 || x >= max || y >= max ) {
			throw new IllegalArgumentException("outside");
		}
		 */

		this.y = y;
		this.x = x;
		this.value = value;
	}


	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "y="+this.y+" x="+this.x+" value="+this.value;
	}
}
