package de.blafoo.geocaching.GC2X4XP;
import java.util.ArrayList;
import java.util.Arrays;

public class Col extends RowCol {
	
	protected Col(int lengthOfCol, Block[] blocks) {
		super(lengthOfCol, blocks);
	}
	
	public Col(int lengthOfCol, int... values) {
		super(lengthOfCol);

		int lengthOfLine = 0;
		for (int value : values) {
			lengthOfLine += value + 1;
		}
		lengthOfLine--;
		if ( lengthOfLine > lengthOfCol) {
			throw new IllegalArgumentException("Länge der Blöcke größer als verfügbares Feld.");
		}
		
		if ( values.length == 0 ) {
			// Leere Zeile
			blocks = new Block[1];
			blocks[0] = new Block(0,lengthOfCol);
		} else {
			blocks = new Block[values.length+1];
			// Leerer Dummy-Block
			blocks[0] = new Block(0,0);
		
			for (int i = 0; i < values.length; i++) {
				blocks[i+1] = new Block(values[i], i < values.length-1 ? 1 : 0);
			}
		}
	}
	
	public Col(int lengthOfCol) {
		super(lengthOfCol);
	}
	
	public static boolean canColBeUsed(Col col, final int x) {
		if ( null == RowCol.field ) {
			return true;
		}
		for (int y = 0; y < col.lengthOfRowCol; y++) {
			switch (field[y].charAt(x)) {
			case 'X':
				if ( !col.isBlock(y)) {
					return false;
				}
				break;
			case ' ':
				if ( col.isBlock(y)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void getCombinations(ArrayList<Col> result, final Block[] actualCombination, int blockNumber, final int x) {
		
		//System.out.println("BN="+blockNumber);
		
		if ( blockNumber >= actualCombination.length ) {
			return;
		}
		
		Block[] workingSet = actualCombination;
		while ( RowCol.getTotalLength(workingSet) < lengthOfRowCol) {
			getCombinations(result, workingSet, blockNumber+1, x);
		
			Block expanded = workingSet[blockNumber].increase();
			workingSet = Arrays.copyOf(workingSet,workingSet.length);
			workingSet[blockNumber] = expanded;
		
		}
		// Länge der Blocks == Länge der Zeile -> Zeile komplett ausgefüllt
		Col col = new Col(this.lengthOfRowCol, workingSet);
		if ( Col.canColBeUsed(col,x)) {
			result.add(col);
		}
	}
	
	public ArrayList<Col> getCombinations(final int x) {
		ArrayList<Col> result = new ArrayList<Col>();
		getCombinations(result, blocks,  0, x);
		if ( result.size() == 0 ) {
			System.out.println("Keine Kombination ermittelt für Spalte="+x);
			throw new RuntimeException();
		}
		return result;
	}
	
	/*
	public static String staticAnalysis(ArrayList<Col> cols) {
		ArrayList<RowCol> convert = new ArrayList<RowCol>(cols.size());
		for ( Col col: cols ) {
			convert.add(col);
		}
		return RowCol.staticAnalysis(convert);
	}
	
	public static void dump(ArrayList<Col> cols) {
		ArrayList<RowCol> convert = new ArrayList<RowCol>(cols.size());
		for ( Col col: cols ) {
			convert.add(col);
		}
		RowCol.dump(convert);
	}
	*/
}
