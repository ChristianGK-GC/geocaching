package de.blafoo.geocaching.GC2X4XP;

import java.util.ArrayList;

public class Board {
	
	private double possibleSolutions = 0;
	private int validSolutions = 0;
	
	private final int dimensionX;
	private final int dimensionY;
	
	private Row[] rows = null;
	private Col[] cols = null;
	
	public Board(int dimensionX, int dimensionY) {
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		rows = new Row[dimensionY];
		cols = new Col[dimensionX];
	}
	
	public Row[] getRows() {
		return rows;
	}
	
	public void setRows(Row... rows) {
		if ( rows.length != dimensionY) {
			throw new IllegalArgumentException("Falsche Anzahl Zeilen.");
		}
		int i=0;
		for (Row row : rows) {
			this.rows[i++] = row;
		}
	}
	
	public Col[] getCols() {
		return cols;
	}
	
	public void setCols(Col... cols) {
		if ( cols.length != dimensionX) {
			throw new IllegalArgumentException("Falsche Anzahl Spalten.");
		}
		int i=0;
		for (Col col : cols) {
			this.cols[i++] = col;
		}
	}
	
	public int getDimX() {
		return dimensionX;
	}
	
	public int getDimY() {
		return dimensionY;
	}
	
	public void printField(Row[] board) {
		// System.out.println("-->");
		for (int y = 0; y < dimensionY; y++) {
			System.out.println(y + ":" + board[y].toString() + ":");
		}
		// System.out.println("<--");
	}
	
	private boolean isValidSolution(Row[] board) {

		for (int x=0; x < dimensionX; x++) {
			// Einen Abziehen wegen Dummy-Block
			int numberOfBlocks = cols[x].getBlocks().length-1;
			int numberOfConstructedBlocks = 0;
			Block newBlock = null;
			int len = 0;
			
			for (int y=0; y < dimensionY; y++) {
				if ( board[y].isBlock(x) ) {
					len++;
				} else {
					if ( len > 0 ) {
						newBlock = new Block(len,-1);
						len = 0;
						numberOfConstructedBlocks++;
						if ( numberOfConstructedBlocks > numberOfBlocks ||
							 !cols[x].getBlocks()[numberOfConstructedBlocks-1].isEqual(newBlock)) {
							return false;
						}
					}
				}
			}
			if ( len > 0 ) {
				newBlock = new Block(len,-1);
				len = 0;
				numberOfConstructedBlocks++;
				if ( numberOfConstructedBlocks > numberOfBlocks ||
					 !cols[x].getBlocks()[numberOfConstructedBlocks-1].isEqual(newBlock)) {
					return false;
				}
			}
		
			// Blocks vergleichen
			if ( numberOfBlocks != numberOfConstructedBlocks) {
				return false;
			}
		}
		return true;
	}
	
	private void findSolutions(Row[][] combinations, Row[] board, final int y, int counter) {
		
		// System.out.print("y="+y);
		if ( y >= dimensionY ) {
		
			possibleSolutions++;
			if ( counter == 1000000 ) {
				System.out.print(".");
				counter = 0;
			}
	
			if ( isValidSolution(board) ) {
				validSolutions++;
				System.out.println();
				System.out.println("Lösung "+validSolutions);
				printField(board);
			}
			
			return;
		}
		
		for (int i=0; i < combinations[y].length; i++) {
			board[y] = combinations[y][i];
			findSolutions(combinations, board, y+1,++counter);
		}
	}
	
	public void findSolutions() {
		
		Row[] board = new Row[dimensionY];
		Row[][] allCombinations = new Row[dimensionY][];
		System.out.println("Erstelle alle möglichen Kombinationen");
		double possibleCombinations = 1;
		for (int y=0; y < dimensionY; y++) {
			System.out.print("Row #"+y);
			System.out.print(" Länge= "+rows[y].getTotalLength());
			ArrayList<Row> combinations = rows[y].getCombinations(y);
			allCombinations[y] = combinations.toArray(new Row[0]);
			possibleCombinations *= combinations.size();
			System.out.println(" Kombinationen="+combinations.size());
		}
		
		System.out.println("Suche nach Lösungen, Anzahl der Kombinationen="+possibleCombinations);
		int counter = 0;
		findSolutions(allCombinations,board,0,counter);
		
		System.out.println();
		System.out.println("Mögliche Lösungen="+possibleSolutions);
		System.out.println("Gültige Lösungen="+validSolutions);
	}

}
