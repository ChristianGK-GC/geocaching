package de.blafoo.geocaching.GC2X4XP;

public class Block  {
	
	public final static char BLOCK = '*';
	public final static char SPACE = '-';

	final int blockSize;
	int spaceSize = 0;
	int totalSize = 0;
	
	String asString = null;

	public Block(int blockSize, int spaceSize) {
		this.blockSize = blockSize;
		this.spaceSize = spaceSize;
		this.totalSize = blockSize+spaceSize;
	}
	
	private String asString() {
		StringBuffer sb = new StringBuffer(this.totalSize);
		for (int i=0; i < this.blockSize; i++) {
			sb.append(BLOCK);
		}
		for (int i=0; i < this.spaceSize; i++) {
			sb.append(SPACE);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		if ( asString == null ) {
			asString = asString();
		}
		return asString;
	}
	
	public int getTotalLength() {
		return this.totalSize;
	}
	
	public Block increase() {
		return new Block(blockSize, spaceSize+1);
	}
	
	public boolean isEqual(Block obj) {
		return this.blockSize == obj.blockSize;
	}

}
