package de.blafoo.geocaching.GC3AR1M;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * GC3AR1M - Magisches Quadrat
 * 
 * @author blafoo
 *
 */
public class MagischesQuadrat {

	private static final int SIZE = 10;

	private static final int EMPTY = 0;

	private static final boolean DIRECTION = true; // bei 1 Starten...

	private static final boolean FIXED = true; // gibt es eine Vorbelegung
	private static Set<Field> PREDEFINEDFIELDS;
	static {
		MagischesQuadrat.PREDEFINEDFIELDS = new HashSet<Field>();
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(1,1,MagischesQuadrat.SIZE,23));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(1,7,MagischesQuadrat.SIZE,45));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(2,8,MagischesQuadrat.SIZE,32));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(4,3,MagischesQuadrat.SIZE,18));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(4,4,MagischesQuadrat.SIZE,1));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(4,9,MagischesQuadrat.SIZE,28));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(5,2,MagischesQuadrat.SIZE,100));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(8,0,MagischesQuadrat.SIZE,81));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(8,7,MagischesQuadrat.SIZE,57));
		MagischesQuadrat.PREDEFINEDFIELDS.add(new Field(8,8,MagischesQuadrat.SIZE,62));
	}

	private static final int[][] board = new int[MagischesQuadrat.SIZE][MagischesQuadrat.SIZE];

	private static int numberSolutions = 0;
	private static int numberOfJumps = 0;

	public static void testValidMoves() {
		System.out.println("(1,1)");
		for ( final Field field: MagischesQuadrat.getValidMoves(1,1) ) {
			System.out.println(field.toString());
		}
		System.out.println("(8,9)");
		for ( final Field field: MagischesQuadrat.getValidMoves(8,9) ) {
			System.out.println(field.toString());
		}
		System.out.println("(5,5)");
		for ( final Field field: MagischesQuadrat.getValidMoves(5,5) ) {
			System.out.println(field.toString());
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(final String[] args) {

		//Solver.testValidMoves();

		final Time start = new Time(System.currentTimeMillis());
		System.out.println("Starting at " + start.toLocaleString());
		MagischesQuadrat.init();
		MagischesQuadrat.jump(MagischesQuadrat.DIRECTION ? 4:5, MagischesQuadrat.DIRECTION ? 4:2, MagischesQuadrat.DIRECTION ? 1 : 100);
		final Time stop = new Time(System.currentTimeMillis());
		System.out.println("Finished at " + stop.toLocaleString());
		System.out.println("Solutions="+MagischesQuadrat.numberSolutions);
	}

	private static void init() {

		for (int y = 0; y < MagischesQuadrat.SIZE; y++) {
			for (int x = 0; x < MagischesQuadrat.SIZE; x++) {
				MagischesQuadrat.board[y][x] = MagischesQuadrat.EMPTY;
			}
		}

		if (MagischesQuadrat.FIXED) {
			// given numbers
			for (final Field field : MagischesQuadrat.PREDEFINEDFIELDS) {
				MagischesQuadrat.board[field.getY()][field.getX()] = field.getValue();
			}
		}
	}

	private static void debug() {
		for (int y = 0; y < MagischesQuadrat.SIZE; y++) {
			for (int x = 0; x < MagischesQuadrat.SIZE; x++) {
				System.out.print(String.format("%2d ", MagischesQuadrat.board[y][x]));
			}
			System.out.println();
		}
		System.out.println();
	}

	private static Set<Field> getValidMoves(final int y, final int x) {
		final Set<Field> fields = new HashSet<Field>();
		if ( y-3 >= 0 ) {
			fields.add(new Field(y-3, x, MagischesQuadrat.SIZE));
		}
		if ( y+3 < MagischesQuadrat.SIZE ) {
			fields.add(new Field(y+3, x, MagischesQuadrat.SIZE));
		}
		if ( x-3 >= 0 ) {
			fields.add(new Field(y, x-3, MagischesQuadrat.SIZE));
		}
		if ( x+3 < MagischesQuadrat.SIZE ) {
			fields.add(new Field(y, x+3, MagischesQuadrat.SIZE));
		}
		if ( y-2 >= 0 && x-2 >= 0) {
			fields.add(new Field(y-2, x-2, MagischesQuadrat.SIZE));
		}
		if ( y-2 >= 0 && x+2 < MagischesQuadrat.SIZE) {
			fields.add(new Field(y-2, x+2, MagischesQuadrat.SIZE));
		}
		if ( y+2 < MagischesQuadrat.SIZE && x-2 >= 0) {
			fields.add(new Field(y+2, x-2, MagischesQuadrat.SIZE));
		}
		if ( y+2 < MagischesQuadrat.SIZE && x+2 < MagischesQuadrat.SIZE) {
			fields.add(new Field(y+2, x+2, MagischesQuadrat.SIZE));
		}
		return fields;
	}


	private static void jump(final int y, final int x, final int num) {

		boolean fixed = false;
		if (MagischesQuadrat.board[y][x] != MagischesQuadrat.EMPTY) {
			if (MagischesQuadrat.board[y][x] != num) {
				return; // already visited
			} else {
				fixed = true;
			}
		}

		if (num == ( MagischesQuadrat.DIRECTION ? 101 : 0)) {
			MagischesQuadrat.numberSolutions++;
			MagischesQuadrat.debug();
			throw new IllegalArgumentException("outside");
		}

		if ( MagischesQuadrat.FIXED ) {
			// alle Lösungen die von den Vorgaben abweichen gleich verwerfen
			for (final Field field : MagischesQuadrat.PREDEFINEDFIELDS) {
				if ( field.getValue() == num && (field.getY() != y || field.getX() != x ) ) {
					//System.out.println(field.toString());
					return;
				}
			}
		}

		if (MagischesQuadrat.numberOfJumps++ % 20 == 0) {
			System.out.print(num+".");
		}
		if (MagischesQuadrat.numberOfJumps % 200 == 0) {
			System.out.println();
		}

		if (!fixed) {
			MagischesQuadrat.board[y][x] = num;
		}

		for ( final Field field: MagischesQuadrat.getValidMoves(y,x) ) {
			MagischesQuadrat.jump(field.getY(), field.getX(), MagischesQuadrat.DIRECTION ? num+1 : num-1);
		}

		if (!fixed) {
			MagischesQuadrat.board[y][x] = MagischesQuadrat.EMPTY;
		}

	}

}
