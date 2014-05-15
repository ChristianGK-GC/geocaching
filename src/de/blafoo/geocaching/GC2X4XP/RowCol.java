package de.blafoo.geocaching.GC2X4XP;

import java.util.ArrayList;
import java.util.Arrays;

public class RowCol {

	protected static String[] field = null;
	
	protected final int lengthOfRowCol;
	protected Block[] blocks = new Block[0];
	protected String asString = null;

	public RowCol(int lengthOfRowCol) {
		this.lengthOfRowCol = lengthOfRowCol;
	}
	
	protected RowCol(int lengthOfRowCol, Block[] blocks) {
		this.lengthOfRowCol = lengthOfRowCol;
		this.blocks = blocks;
	}
	
	public int getTotalLength() {
		return RowCol.getTotalLength(blocks);
	}
	
	public static int getTotalLength(final Block[] blocks) {
		int totalLength = 0;
		for (Block block : blocks) {
			totalLength += block.getTotalLength();
		}
		return totalLength;
	}

	public static void setField(String[] newField) {
		boolean change = false;
		if ( newField != null && RowCol.field != null ) {
			for (int i=0; i < RowCol.field.length; i++) {
				System.out.println(newField[i]);
				if ( 0 != newField[i].compareTo(RowCol.field[i]) ) {
					change = true;
				}
			}
			System.out.println("setField-Änderung="+change);
		}
		RowCol.field = newField;
	}
	
	public static String[] getField() {
		return field;
	}

	public static String staticAnalysis(ArrayList<RowCol> rowcols) {
		int[] blocks = new int[rowcols.get(0).lengthOfRowCol];
		int[] noblocks = new int[rowcols.get(0).lengthOfRowCol];
		int counter = 0;
		for ( RowCol rowcol : rowcols) {
			for (int x=0; x < rowcol.lengthOfRowCol; x++) {
				if ( rowcol.isBlock(x) ) {
					blocks[x]++;
				} else {
					noblocks[x]++;
				}
			}
			counter++;
		}
		StringBuffer sb = new StringBuffer(rowcols.get(0).lengthOfRowCol);
		for (int x=0; x < rowcols.get(0).lengthOfRowCol; x++) {
			if ( blocks[x] == counter ) {
				sb.append('X');
			} else 	if ( noblocks[x] == counter ) {
				sb.append(' ');
			} else {
				sb.append('.');
			}
		}
		return sb.toString();
	}

	private String asString() {
		StringBuffer sb = new StringBuffer();
		for (Block block : blocks) {
			sb.append(block.toString());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		if ( null == asString ) {
			asString = asString();
		}
		return asString;
	}

	public boolean isBlock(final int xy) {
		return Block.BLOCK == this.toString().charAt(xy);
	}

	public void addBlock(Block block) {
		blocks = Arrays.copyOf(blocks, blocks.length+1);
		blocks[blocks.length-1] = block;
	}

	public Block[] getBlocks() {
		return blocks;
	}
	
	public static void dump(ArrayList<RowCol> rowcols) {
		for (RowCol rowcol : rowcols) {
			System.out.println(rowcol.toString());
		}
	}
	
	public static <T> ArrayList<RowCol> convert(ArrayList<T> input) {
		ArrayList<RowCol> result = new ArrayList<RowCol>(input.size());
		for (T i : input) {
			result.add((RowCol) i);
		}
		return result;
	}
}