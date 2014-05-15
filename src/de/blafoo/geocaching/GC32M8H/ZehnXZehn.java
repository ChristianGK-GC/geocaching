package de.blafoo.geocaching.GC32M8H;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * http://coord.info/GC32M8H
 * 
 * @author blafoo
 *
 */
public class ZehnXZehn {

	public enum Direction {
		Up,
		Down
	};

	private static final int EMPTY = 0;

	private static final int SIZE = 10;

	private static final Direction DIRECTION = Direction.Up; // bei 1 Starten...

	private static final boolean ALL = false;	// alle oder eine Lösung
	private static final boolean TRACE = false;
	private static final boolean FIXED = true; // gibt es eine Vorbelegung

	private static Set<Field> PREDEFINEDFIELDS;
	static {
		ZehnXZehn.PREDEFINEDFIELDS = new HashSet<Field>();
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(1,1,ZehnXZehn.SIZE,23));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(1,7,ZehnXZehn.SIZE,45));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(1,9,ZehnXZehn.SIZE,27));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(2,8,ZehnXZehn.SIZE,32));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(3,3,ZehnXZehn.SIZE,24));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(3,6,ZehnXZehn.SIZE,60));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(3,7,ZehnXZehn.SIZE,26));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(4,3,ZehnXZehn.SIZE,18));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(4,4,ZehnXZehn.SIZE,1));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(4,9,ZehnXZehn.SIZE,28));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(5,2,ZehnXZehn.SIZE,100));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(5,4,ZehnXZehn.SIZE,59));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(5,5,ZehnXZehn.SIZE,25));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(5,8,ZehnXZehn.SIZE,61));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(8,0,ZehnXZehn.SIZE,81));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(8,4,ZehnXZehn.SIZE,58));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(8,7,ZehnXZehn.SIZE,57));
		ZehnXZehn.PREDEFINEDFIELDS.add(new Field(8,8,ZehnXZehn.SIZE,62));
	}

	private static final int[][] board = new int[ZehnXZehn.SIZE][ZehnXZehn.SIZE];

	private static int numberSolutions = 0;
	private static int numberOfJumps = 0;

	@SuppressWarnings("deprecation")
	public static void main(final String[] args) {

		ZehnXZehn.init();

		final Time start = new Time(System.currentTimeMillis());
		System.out.println("Starting at " + start.toLocaleString());

		if ( ZehnXZehn.FIXED ) {
			if ( Direction.Up == ZehnXZehn.DIRECTION ) {
				ZehnXZehn.jump(4, 4, 1);
			} else {
				ZehnXZehn.jump(5, 2, 100);
			}
		} else {
			ZehnXZehn.jump(0,0,1);
		}

		final Time stop = new Time(System.currentTimeMillis());
		System.out.println("\nFinished at " + stop.toLocaleString());
		System.out.println("Solutions="+ZehnXZehn.numberSolutions);
	}

	private static void init() {

		for (int y = 0; y < ZehnXZehn.SIZE; y++) {
			for (int x = 0; x < ZehnXZehn.SIZE; x++) {
				ZehnXZehn.board[y][x] = ZehnXZehn.EMPTY;
			}
		}

		if (ZehnXZehn.FIXED) {
			// given numbers
			for (final Field field : ZehnXZehn.PREDEFINEDFIELDS) {
				ZehnXZehn.board[field.getY()][field.getX()] = field.getValue();
			}
		}
	}

	private static void debug() {
		System.out.println();
		for (int y = 0; y < ZehnXZehn.SIZE; y++) {
			for (int x = 0; x < ZehnXZehn.SIZE; x++) {
				System.out.print(String.format("%3d ", ZehnXZehn.board[y][x]));
			}
			System.out.println();
		}
		System.out.println();
	}

	public static Set<Field> getValidMoves(final int y, final int x) {
		final Set<Field> fields = new HashSet<Field>();

		// 10x10
		if ( y-3 >= 0 ) {
			fields.add(new Field(y-3, x, ZehnXZehn.SIZE));
		}
		if ( y+3 < ZehnXZehn.SIZE ) {
			fields.add(new Field(y+3, x, ZehnXZehn.SIZE));
		}
		if ( x-3 >= 0 ) {
			fields.add(new Field(y, x-3, ZehnXZehn.SIZE));
		}
		if ( x+3 < ZehnXZehn.SIZE ) {
			fields.add(new Field(y, x+3, ZehnXZehn.SIZE));
		}
		if ( y-2 >= 0 && x-2 >= 0) {
			fields.add(new Field(y-2, x-2, ZehnXZehn.SIZE));
		}
		if ( y-2 >= 0 && x+2 < ZehnXZehn.SIZE) {
			fields.add(new Field(y-2, x+2, ZehnXZehn.SIZE));
		}
		if ( y+2 < ZehnXZehn.SIZE && x-2 >= 0) {
			fields.add(new Field(y+2, x-2, ZehnXZehn.SIZE));
		}
		if ( y+2 < ZehnXZehn.SIZE && x+2 < ZehnXZehn.SIZE) {
			fields.add(new Field(y+2, x+2, ZehnXZehn.SIZE));
		}

		// Springer
		/*
		if ( y-2 >= 0 && x-1 >= 0) {
			fields.add(new Field(y-2, x-1, Solver.SIZE));
		}
		if ( y-2 >= 0 && x+1 < Solver.SIZE) {
			fields.add(new Field(y-2, x+1, Solver.SIZE));
		}
		if ( y+2 < Solver.SIZE && x-1 >= 0) {
			fields.add(new Field(y+2, x-1, Solver.SIZE));
		}
		if ( y+2 < Solver.SIZE && x+1 < Solver.SIZE) {
			fields.add(new Field(y+2, x+1, Solver.SIZE));
		}
		if ( y-1 >= 0 && x-2 >= 0) {
			fields.add(new Field(y-1, x-2, Solver.SIZE));
		}
		if ( y-1 >= 0 && x+2 < Solver.SIZE) {
			fields.add(new Field(y-1, x+2, Solver.SIZE));
		}
		if ( y+1 < Solver.SIZE && x-2 >= 0) {
			fields.add(new Field(y+1, x-2, Solver.SIZE));
		}
		if ( y+1 < Solver.SIZE && x+2 < Solver.SIZE) {
			fields.add(new Field(y+1, x+2, Solver.SIZE));
		}
		*/

		// 3x3
		/*
		if ( y-1 >= 0 ) {
			fields.add(new Field(y-1, x, Solver.SIZE));
		}
		if ( y+1 < Solver.SIZE ) {
			fields.add(new Field(y+1, x, Solver.SIZE));
		}
		if ( x-1 >= 0 ) {
			fields.add(new Field(y, x-1, Solver.SIZE));
		}
		if ( x+1 < Solver.SIZE ) {
			fields.add(new Field(y, x+1, Solver.SIZE));
		}
		 */

		return fields;
	}

	private static boolean isValidSolution() {
		for (int y = 0; y < ZehnXZehn.SIZE; y++) {
			for (int x = 0; x < ZehnXZehn.SIZE; x++) {
				if ( ZehnXZehn.board[y][x] == ZehnXZehn.EMPTY ) {
					return false;
				}
			}
		}
		return true;
	}



	private static void jump(final int y, final int x, final int num) {

		boolean fixed = false;
		if (ZehnXZehn.board[y][x] != ZehnXZehn.EMPTY) {
			if (ZehnXZehn.board[y][x] != num) {
				return; // already visited
			} else {
				fixed = true;
			}
		}

		if ( ZehnXZehn.FIXED ) {
			// alle Lösungen die von den Vorgaben abweichen gleich verwerfen
			for (final Field field : ZehnXZehn.PREDEFINEDFIELDS) {
				if ( field.getValue() == num && (field.getY() != y || field.getX() != x ) ) {
					//System.out.println(field.toString());
					return;
				}
			}
		}

		if ( ZehnXZehn.TRACE ) {
			if (ZehnXZehn.numberOfJumps++ % 20 == 0) {
				System.out.print(num+".");
			}
			if (ZehnXZehn.numberOfJumps % 200 == 0) {
				System.out.println();
			}
		}

		if (!fixed) {
			ZehnXZehn.board[y][x] = num;
		}

		if (num == ( ZehnXZehn.DIRECTION == Direction.Up ? ZehnXZehn.SIZE*ZehnXZehn.SIZE : 1)) {
			if ( ZehnXZehn.isValidSolution() ) {
				ZehnXZehn.numberSolutions++;
				ZehnXZehn.debug();
				if ( !ZehnXZehn.ALL ) {
					throw new IllegalArgumentException("outside");
				}
				if (!fixed) {
					ZehnXZehn.board[y][x] = ZehnXZehn.EMPTY;
				}
				return;
			} else {
				return;
			}
		}

		for ( final Field field: ZehnXZehn.getValidMoves(y,x) ) {
			ZehnXZehn.jump(field.getY(), field.getX(), ZehnXZehn.DIRECTION == Direction.Up ? num+1 : num-1);
		}

		if (!fixed) {
			ZehnXZehn.board[y][x] = ZehnXZehn.EMPTY;
		}

	}

}
