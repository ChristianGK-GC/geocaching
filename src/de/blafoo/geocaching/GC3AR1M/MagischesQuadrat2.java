package de.blafoo.geocaching.GC3AR1M;

import java.sql.Time;

public class MagischesQuadrat2 {
	
	private static final boolean FIXED = true;

	private static final int SIZE = 5;
	
	private static final int MIN = 1;
	private static final int MAX = SIZE*SIZE;
	/** sum required for a row/col = magic number */
	private static final int SUM = (SIZE*SIZE*SIZE + SIZE) / 2;
	
	private static final int[] square = new int[SIZE*SIZE];
	private static final boolean[] numbersAlreadyUsed = new boolean[SIZE*SIZE+1];
	private static final boolean[] fieldsGiven = new boolean[SIZE*SIZE];
	
	private static int numberSolutions = 0;
	private static int iterations = 1;						// 3 = 5611771   4 = 
	
	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Time start = new Time(System.currentTimeMillis());
		System.out.println("Starting at "+start.toLocaleString());
		init();
		iterate(0);
		Time stop = new Time(System.currentTimeMillis());
		System.out.println("Finished at " + stop.toLocaleString() + " after "+iterations+" iterations.");
	}
	
	private static void init() {
		
		for ( int i = 0; i < SIZE*SIZE; i++) {
			square[i] = MIN-1;
			fieldsGiven[i] = false;
			numbersAlreadyUsed[i] = false;
		}
		
		if ( FIXED ) {
			// given numbers

			// Test for 3x3
//			square[4] = 3;
//			numbersAlreadyUsed.set(3);
//			fieldsGiven[4] = true;
			
			
			// N 52° 23.580 E 009° 41.230
			
			// N 52° (H – E + C).( I*M + 3*N*P + D*G - M)
			// E 009° (A*K).( B*F + 5*L + N)
			
			// H-E+C = 22..24
			// A*K = 40 (6*5) oder 42 (6*7)
			
			square[0*SIZE+0] = 1+1;
			square[0*SIZE+4] = 1+22;
			square[1*SIZE+1] = 1+24;
			square[1*SIZE+3] = 1+11;
			square[2*SIZE+2] = 1+5;
			square[3*SIZE+1] = 1+3;
			square[3*SIZE+3] = 1+17;
			square[4*SIZE+0] = 1+19;
			square[4*SIZE+4] = 1+13;
			numbersAlreadyUsed[square[0*SIZE+0]] = true;
			numbersAlreadyUsed[square[0*SIZE+4]] = true;
			numbersAlreadyUsed[square[1*SIZE+1]] = true;
			numbersAlreadyUsed[square[1*SIZE+3]] = true;
			numbersAlreadyUsed[square[2*SIZE+2]] = true;
			numbersAlreadyUsed[square[3*SIZE+1]] = true;
			numbersAlreadyUsed[square[3*SIZE+3]] = true;
			numbersAlreadyUsed[square[4*SIZE+0]] = true;
			numbersAlreadyUsed[square[4*SIZE+4]] = true;
			fieldsGiven[0*SIZE+0] = true;
			fieldsGiven[0*SIZE+4] = true;
			fieldsGiven[1*SIZE+1] = true;
			fieldsGiven[1*SIZE+3] = true;
			fieldsGiven[2*SIZE+2] = true;
			fieldsGiven[3*SIZE+1] = true;
			fieldsGiven[3*SIZE+3] = true;
			fieldsGiven[4*SIZE+0] = true;
			fieldsGiven[4*SIZE+4] = true;
		}
	}
	
	private static void debug() {
		for ( int y = 0; y < SIZE; y++) {
			for ( int x = 0; x < SIZE; x++) {
				System.out.print(String.format("%2d ", square[y*SIZE+x]));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static boolean check() {
		int[] col = new int[SIZE];
		
		for ( int y = 0; y < SIZE; y++) {
			
			int row = 0;
			int index = y*SIZE;
			for ( int x = 0; x < SIZE; x++) {
				row += square[index+x];
				col[x] += square[index+x];
			}
			if ( row != SUM ) {
				return false;
			}
		}
		
		for ( int x = 0; x < SIZE; x++) {
			if ( col[x] != SUM ) {
				return false;
			}
		}
		return true;
	}
	
	private static void iterate(final int field) {
		if (field >= SIZE*SIZE) {
			if ( check() ) {
				System.out.println();
				System.out.println("Solution #" + numberSolutions++);
				debug();
			}
			return;
		}
		if ( fieldsGiven[field] ) {
			iterate(field+1);
		} else {
			for ( int v = MIN; v <= MAX; v++) {
				if ( iterations % 10000000 == 0) {
					System.out.print(".");
				}
				/*
				if ( iterations % 1000000000 == 0) {
					System.out.println();
				}
				*/
				iterations++;
				
				// Optimierung
				if ( FIXED ) {
					if ( field == 1 ) {
						if ( v < 5 || v > 7 ) {
							continue;
						}
					} else if ( field == 14) {
						if ( v < 5 || v > 7 ) {
							continue;
						}
					}
				}
				
				if ( numbersAlreadyUsed[v] ) {
					continue;
				}
				
				square[field] = v;
				numbersAlreadyUsed[v] = true;
				iterate(field+1);
				numbersAlreadyUsed[v] = false;
			}
		}
	}

}
