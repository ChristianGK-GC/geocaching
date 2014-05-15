package de.blafoo.geocaching.GC2X4XP;
import java.util.ArrayList;
import java.util.Arrays;


public class Row extends RowCol {
	
	protected Row(int lengthOfRow, Block[] blocks) {
		super(lengthOfRow, blocks);
	}
	
	public Row(int lengthOfRow, final int... values) {
		super(lengthOfRow);

		// Kontrolle der Länge der Eingabe-Blöcke
		int lengthOfLine = 0;
		for (int value : values) {
			lengthOfLine += value + 1;
		}
		lengthOfLine--;
		if ( lengthOfLine > lengthOfRow) {
			throw new IllegalArgumentException("Länge der Blöcke größer als verfügbares Feld.");
		}
	
		if ( values.length == 0 ) {
			// Leere Zeile
			blocks = new Block[1];
			blocks[0] = new Block(0,lengthOfRow);
		} else {
			blocks = new Block[values.length+1];
			// Leerer Dummy-Block
			blocks[0] = new Block(0,0);
		
			for (int i = 0; i < values.length; i++) {
				blocks[i+1] = new Block(values[i], i < values.length-1 ? 1 : 0);
			}
		}
			
	}
	
	public static boolean canRowBeUsed(Row row, final int y) {
		if ( null == Row.field ) {
			return true;
		}
		String knownSoFar = Row.field[y];
		for (int x = 0; x < knownSoFar.length(); x++) {
			switch (knownSoFar.charAt(x)) {
			case 'X':
				if ( !row.isBlock(x)) {
					return false;
				}
				break;
			case ' ':
				if ( row.isBlock(x)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void getCombinations(ArrayList<Row> result, final Block[] actualCombination, int blockNumber, final int y) {
		
		//System.out.println("BN="+blockNumber);
		
		if ( blockNumber >= actualCombination.length ) {
			return;
		}
		
		Block[] workingSet = actualCombination;
		while ( RowCol.getTotalLength(workingSet) < lengthOfRowCol) {
			getCombinations(result, workingSet, blockNumber+1, y);
		
			Block expanded = workingSet[blockNumber].increase();
			workingSet = Arrays.copyOf(workingSet,workingSet.length);
			workingSet[blockNumber] = expanded;
		
		}
		// Länge der Blocks == Länge der Zeile -> Zeile komplett ausgefüllt
		Row row = new Row(this.lengthOfRowCol, workingSet);
		if ( Row.canRowBeUsed(row,y)) {
			result.add(row);
		}
	}
	
	public ArrayList<Row> getCombinations(final int y) {
		ArrayList<Row> result = new ArrayList<Row>();
		getCombinations(result, blocks,  0, y);
		if ( result.size() == 0 ) {
			System.out.println("Keine Kombination ermittelt für Spalte="+y);
			throw new RuntimeException();
		}
		return result;
	}
	
	/*
	public static String staticAnalysis(ArrayList<Row> rows) {
		ArrayList<RowCol> convert = new ArrayList<RowCol>(rows.size());
		for ( Row row : rows ) {
			convert.add(row);
		}
		return RowCol.staticAnalysis(convert);
	}
	*/

}
