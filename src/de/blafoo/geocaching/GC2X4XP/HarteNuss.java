package de.blafoo.geocaching.GC2X4XP;

import java.util.ArrayList;


/**
 * Hart Nuss - GC2X4XP
 * 
 * @author blafoo
 *
 */
public class HarteNuss {
	
	public static final Col COL6 = new Col(37, 7,1,1,1,1,1,1,1,1,1,1,1,7);
	
	private static void analyse(Board board) {
		
		String[] field1 = new String[board.getDimY()];
		
		System.out.println("Statische Analyse Rows");
		for (int y=0; y < board.getDimY(); y++) {
			ArrayList<Row> combinations = board.getRows()[y].getCombinations(y);
			//System.out.println("Kombinationsmöglichkeiten="+combinations.size());
			String result = RowCol.staticAnalysis(RowCol.convert(combinations));
			field1[y] = result;
			
		}
		RowCol.setField(field1);
		System.out.println();
		
		System.out.println("Statische Analyse Cols");
		String[] results = new String[board.getDimX()];
		for (int x=0; x < board.getDimX(); x++) {
			ArrayList<Col> combinations = board.getCols()[x].getCombinations(x);
			/*
			System.out.println("Kombinationsmöglichkeiten für Zeile= "+x+" ="+combinations.size());
			if (x==11 || x==12 || x==28) {
				Col.dump(combinations);
			}
			*/
			results[x] = Col.staticAnalysis(RowCol.convert(combinations));
			//System.out.println(results[x]);
		}
		System.out.println();
		
		String[] field2 = new String[board.getDimY()];
		
		System.out.println("Statische Analyse Cols (gedreht)");
		for (int y=0; y < board.getDimY(); y++) {
			StringBuffer sb = new StringBuffer(board.getDimX());
			for (int x=0; x < board.getDimX(); x++) {
				sb.append(results[x].charAt(y));
			}
			field2[y] = sb.toString();
		}
		RowCol.setField(field2);
		System.out.println();
		analyse(board);

	}
	
	private static void solveIt() {

		final int dimX = 37;
		final int dimY = 37;
		
		Board board = new Board(dimX, dimY);
		
		String[] field1 = new String[]{ 
				"XXXXXXX . ..X. XXXXX X  XX X  XXXXXXX",
				"X     X . .. X   X  XXX XX    X     X",
				"X XXX X  X X XXX X XXXXXX XXX X XXX X",
				"X XXX X X ..X.X . XX     XXXX X XXX X",
				"X XXX X XX . . .. X  . ..  XX X XXX X",
				"X     X X X  .X. .X. .X. XX X X     X",
				"XXXXXXX X X X X X X X X X X X XXXXXXX",
				"        XX  XX   .X. .X. XXXX        ",
				"X   X XXXXXXXXXX  X  .X.  XXXXXXXX ..",
				" X  XX X X    X .. .X. XXX X X XX  ..",
				" XXXXXX   X  .X..X. XXXX X XXX   . ..",
				" X XXX XX  .  X .... . ..  XX .... . ",
				"XXXXX XX XX  . . X.XX......  XXX XXXX",
				" XXX     X   XX .... ........ ...X . ",
				"X  XXXXXX  X .XX...........X.....X.. ",
				"XXX    XXXXXX XX ..........X.....X...",
				" X XX XX XXX XXXX .........XX.....X..",
				"   XXX   XXXXX   ..........X.........",
				"XXX  XXXX X X XX ..... ....X.........",
				" X X   X X   XXXXX ..XXXX......X.....",
				"   X  XX  X XXX ......X...........X..",
				"XX     X XX X X ...... ......X.......",
				"X XXX XX XX  .XXX............X.......",
				"     X XXXX XX  ...............X.....",
				" X X XXX X X ..................X..X. ",
				"XXXXXX XX XX .................X......",
				"  XXXXX   XXX .......................",
				"  X X  XXX X ... ...........X. ......",
				"XXX  XXXXXXX ..XXX.....XX...XXXXXXX..",
				"        X XXXXXX ......X....X. ......",
				"XXXXXXX XXX X ..............X.X .....",
				"X     X    X ...X...........X. ......",
				"X XXX X ...XX.. X ............X......",
				"X XXX X ... X.................XX ....",
				"X XXX X .. X .................XX.....",
				"X     X  ..X..................X......",
				"XXXXXXX ...X..................X......"
		};
		// Unterschied zu field1: Spalte 12, 1'er festgelegt, 2 Möglichkeiten: vorletzter oder letzer
		String[] field2 = new String[]{ 
				"XXXXXXX . ..X. XXXXX X  XX X  XXXXXXX",
				"X     X . .. X   X  XXX XX    X     X",
				"X XXX X  X X XXX X XXXXXX XXX X XXX X",
				"X XXX X X ..X.X . XX     XXXX X XXX X",
				"X XXX X XX . . .. X  . ..  XX X XXX X",
				"X     X X X  .X. .X. .X. XX X X     X",
				"XXXXXXX X X X X X X X X X X X XXXXXXX",
				"        XX  XX   .X. .X. XXXX        ",
				"X   X XXXXXXXXXX  X  .X.  XXXXXXXX ..",
				" X  XX X X    X .. .X. XXX X X XX  ..",
				" XXXXXX   X  .X..X. XXXX X XXX   . ..",
				" X XXX XX  .  X .... . ..  XX .... . ",
				"XXXXX XX XX  . . X.XX......  XXX XXXX",
				" XXX     X   XX .... ........ ...X . ",
				"X  XXXXXX  X .XX...........X.....X.. ",
				"XXX    XXXXXX XX ..........X.....X...",
				" X XX XX XXX XXXX .........XX.....X..",
				"   XXX   XXXXX   ..........X.........",
				"XXX  XXXX X X XX ..... ....X.........",
				" X X   X X   XXXXX ..XXXX......X.....",
				"   X  XX  X XXX ......X...........X..",
				"XX     X XX X X ...... ......X.......",
				"X XXX XX XX  .XXX............X.......",
				"     X XXXX XX  ...............X.....",
				" X X XXX X X ..................X..X. ",
				"XXXXXX XX XX .................X......",
				"  XXXXX   XXX .......................",
				"  X X  XXX X ... ...........X. ......",
				"XXX  XXXXXXX ..XXX.....XX...XXXXXXX..",
				"        X XXXXXX ......X....X. ......",
				"XXXXXXX XXX X ..............X.X .....",
				"X     X    X ...X...........X. ......",
				"X XXX X ...XX.. X ............X......",
				"X XXX X ... X.................XX ....",
				"X XXX X .. X .................XX.....",
				"X     X  ..X .................X......",
				"XXXXXXX ...XX.................X......"
		};
		
		// Ergebnis von field1
		String[] field3 = new String[]{
		"XXXXXXX . ..X. XXXXX X  XX X  XXXXXXX",
		"X     X . .. X   X  XXX XX    X     X",
		"X XXX X  X X XXX X XXXXXX XXX X XXX X",
		"X XXX X X ..X.X . XX     XXXX X XXX X",
		"X XXX X XX . . .. X  X ..  XX X XXX X",
		"X     X X X  .X. .X. XX  XX X X     X",
		"XXXXXXX X X X X X X X X X X X XXXXXXX",
		"        XX  XX   .X.  XX XXXX        ",
		"X   X XXXXXXXXXX  X   XX  XXXXXXXX ..",
		" X  XX X X    X .. .X. XXX X X XX  ..",
		" XXXXXX   X  XX .X. XXXX X XXX   . ..",
		" X XXX XX  X  X .X.  X     XX XX X   ",
		"XXXXX XX XX  . X X.XX.X . X  XXX XXXX",
		" XXX     X   XX ...  . . XXXX   XX X ",
		"X  XXXXXX  X .XX. .. XXX   XXXXX XXX ",
		"XXX    XXXXXX XX    XX ..  XXX   XX  ",
		" X XX XX XXX XXXX  XX  .. XXX X  .XX.",
		"   XXX   XXXXX    XX XXX XXX X XX  X ",
		"XXX  XXXX X X XX XXX X     XXXX XXX  ",
		" X X   X X   XXXXX XXXXXX    X XXXX X",
		"   X  XX  X XXX  .. XXXXX  X XXX  XXX",
		"XX     X XX X X   X  X   XXXXX  X  X ",
		"X XXX XX XX   XXXX     X   XXXXX XXX ",
		"     X XXXX XX  XXXX  XX   XX  XX XXX",
		" X X XXX X X  XX  XX  X X X   XX  XX ",
		"XXXXXX XX XX    X  XXX X XX X XXX   X",
		"  XXXXX   XXX   XXX X X X XXX    XX  ",
		"  X X  XXX X XX  XX  X      XX X XX  ",
		"XXX  XXXXXXX XXXXX X XXXX  XXXXXXXXX ",
		"        X XXXXXX XX    XXXX X   XX  X",
		"XXXXXXX XXX X  XX X XXXXX   X X X    ",
		"X     X    X XXXX  XXX XX  XX   X XXX",
		"X XXX X X  XXX  X   X     X XXXXX X  ",
		"X XXX X  XX X    XXX XXXX     XX   X ",
		"X XXX X    X   X  XX         XXX X X ",
		"X     X   XXX XXX  XXX X  XXX XX XXX ",
		"XXXXXXX XX X   X XX XXX X    XX XXXXX"
		};
		
		String[] field = field2;
		if ( field.length != dimY ) {
			return;
		}
		Row.setField(field);
		
		board.setRows(
				new Row(dimX,           7,1,2,5,1,2,1,7),
				new Row(dimX,         1,1,1,1,1,3,2,1,1),
				new Row(dimX,   1,3,1,1,1,3,1,6,3,1,3,1),
				new Row(dimX,     1,3,1,1,3,1,2,4,1,3,1),
				
				new Row(dimX, 1,3,1,2,1,1,1,1,1,2,1,3,1),
				new Row(dimX,     1,1,1,1,2,2,2,2,1,1,1),
				new Row(dimX, 7,1,1,1,1,1,1,1,1,1,1,1,7),
				new Row(dimX,                 2,2,2,2,4),
				new Row(dimX,            1,1,10,1,2,8,1),
				
				new Row(dimX,   1,2,1,1,1,1,2,3,1,1,2,1),
				new Row(dimX,           6,1,2,2,4,1,3,1),
				new Row(dimX,       1,3,2,1,1,2,1,2,2,1),
				new Row(dimX,       5,2,2,1,1,4,1,1,3,4),
				new Row(dimX,           3,1,2,1,1,4,2,1),
				
				new Row(dimX,           1,6,1,3,1,3,5,3),
				new Row(dimX,             3,6,2,2,1,3,2),
				new Row(dimX,       1,2,2,3,4,2,1,3,1,3),
				new Row(dimX,           3,5,2,3,3,1,2,1),
				new Row(dimX,         3,4,1,1,2,3,1,4,3),
				
				new Row(dimX,         1,1,1,1,5,6,1,4,1),
				new Row(dimX,         1,2,1,3,1,5,1,3,3),
				new Row(dimX,       2,1,2,1,1,1,1,5,1,1),
				new Row(dimX,           1,3,2,2,4,1,5,3),
				new Row(dimX,           1,4,2,4,2,2,2,3),
				
				new Row(dimX,   1,1,3,1,1,2,2,1,1,1,2,2),
				new Row(dimX,       6,2,2,1,3,1,2,1,3,1),
				new Row(dimX,           5,3,3,1,1,1,3,2),
				new Row(dimX,       1,1,3,1,2,2,1,2,1,2),
				new Row(dimX,               3,7,5,1,4,9),
				
				new Row(dimX,             1,6,2,4,1,2,1),
				new Row(dimX,         7,3,1,2,1,5,1,1,1),
				new Row(dimX,         1,1,1,4,3,2,2,1,3),
				new Row(dimX,       1,3,1,1,3,1,1,1,5,1),
				new Row(dimX,         1,3,1,2,1,3,4,2,1),
				
				new Row(dimX,         1,3,1,1,1,2,3,1,1),
				new Row(dimX,         1,1,3,3,3,1,3,2,3),
				new Row(dimX,         7,2,1,1,2,3,1,2,5)
		);

		board.setCols(
				new Col(dimY, 7,1,1,2,1,2,1,1,7),
				new Col(dimY, 1,1,5,2,2,1,2,1,1,1),
				new Col(dimY, 1,3,1,1,2,1,1,1,4,1,3,1),
				new Col(dimY, 1,3,1,5,2,2,1,3,1,3,1),
				
				new Col(dimY, 1,3,1,5,1,2,1,3,1,3,1),
				new Col(dimY, 1,1,3,1,2,4,1,1,1),
				new Col(dimY, 7,1,1,1,1,1,1,1,1,1,1,1,7),
				new Col(dimY, 2,2,3,8,2),
				new Col(dimY, 1,6,1,2,1,1,1,4,1,1),
				
				new Col(dimY, 1,1,3,2,3,1,4,2,1,1,1),
				new Col(dimY, 1,2,1,1,1,4,4,2,3,1,1),
				new Col(dimY, 3,1,1,4,6,2,3),
				new Col(dimY, 1,1,3,1,2,2,1,1,2,2,1),
				new Col(dimY, 3,1,2,1,2,2,2,1,3,2),
				
				new Col(dimY, 2,2,4,4,5,1,3,1,1),
				new Col(dimY, 1,1,1,1,1,3,2,1,1,4,2),
				new Col(dimY, 1,1,1,2,1,1,2,2,1,3,1),
				new Col(dimY, 3,1,4,3,2,4,1,1),
				new Col(dimY, 1,6,1,1,2,1,2,2,2,2,1),
				
				new Col(dimY, 1,2,1,1,1,4,3,1,1,3),
				new Col(dimY, 2,1,2,1,2,2,2,3,2),
				new Col(dimY, 3,2,3,2,5,1,2,2,1,2),
				new Col(dimY, 2,4,1,1,1,1,2,2,1,1,1,1,1),
				new Col(dimY, 1,1,4,2,1,2,2,1,4,1,1),
				
				new Col(dimY, 3,1,1,1,2,2,1,1,4,1,1),
				new Col(dimY, 2,1,1,1,2,1,1,1,1,1),
				new Col(dimY, 2,4,2,2,1,3,1,1,1),
				new Col(dimY, 1,3,5,6,4,1,1,1,1),
				new Col(dimY, 7,2,4,1,3,8,1),
				
				new Col(dimY, 3,1,2,6,2,1,1,1),
				new Col(dimY, 7,1,2,1,1,1,1,1,2,1,1,5),
				new Col(dimY, 1,1,2,2,1,1,2,4,2,4),
				new Col(dimY, 1,3,1,2,1,3,1,1,1,5,1),
				new Col(dimY, 1,3,1,1,6,2,1,4,3),
				
				new Col(dimY, 1,3,1,1,3,3,3,3,2,2),
				new Col(dimY, 1,1,1,3,2,5,1,1,4),
				new Col(dimY, 7,1,1,1,2,1,1,1,1,1)
		);
		
		analyse(board);
		
		board.findSolutions();
	}

	/*
	 * Aufruf: java -Xms2G de.blafoo.nuss.NussKnacker
	 * 
	 * Anzahl der Kombinationen=3.24884038568594E163 (ohne Filter)
	 * Anzahl der Kombinationen=1.1828493290660715E154
	 */

	public static void main(String[] args) {
		System.out.println("NussKnacker Start um "+System.currentTimeMillis());
		
		//testIt();
		solveIt();
		System.out.println("NussKnacker Ende um "+System.currentTimeMillis());
	}

}
