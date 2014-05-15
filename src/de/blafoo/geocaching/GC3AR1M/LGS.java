package de.blafoo.geocaching.GC3AR1M;

import java.sql.Time;
import java.util.BitSet;

public class LGS {

	private static final boolean FIXED = true;

	private static final int SIZE = 5;

	private static final int MIN = 0;
	private static final int MAX = SIZE*SIZE-1;
	/** sum required for a row/col = magic number */
	private static final int SUM = 60; // (SIZE*SIZE*SIZE + SIZE) / 2;

	private static final int[][] square = new int[SIZE][SIZE];
	private static final BitSet numbersAlreadyUsed = new BitSet();

	private static int numberSolutions = 0;

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Time start = new Time(System.currentTimeMillis());
		System.out.println("Starting at "+start.toLocaleString());
		init();
		iterate();
		Time stop = new Time(System.currentTimeMillis());
		System.out.println("Finished at " + stop.toLocaleString());
	}

	private static void init() {

		for ( int y = 0; y < SIZE; y++) {
			for ( int x = 0; x < SIZE; x++) {
				square[y][x] = MIN-1;
			}
		}

		if ( FIXED ) {
			// given numbers

			square[0][0] = 1;
			square[0][4] = 22;
			square[1][1] = 24;
			square[1][3] = 11;
			square[2][2] = 5;
			square[3][1] = 3;
			square[3][3] = 17;
			square[4][0] = 19;
			square[4][4] = 13;

			numbersAlreadyUsed.set(1);
			numbersAlreadyUsed.set(22);
			numbersAlreadyUsed.set(24);
			numbersAlreadyUsed.set(11);
			numbersAlreadyUsed.set(5);
			numbersAlreadyUsed.set(3);
			numbersAlreadyUsed.set(17);
			numbersAlreadyUsed.set(19);
			numbersAlreadyUsed.set(13);

		}
	}

	private static void debug() {
		for ( int y = 0; y < SIZE; y++) {
			for ( int x = 0; x < SIZE; x++) {
				System.out.print(String.format("%2d ", square[y][x]));
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void iterate() {

		for (int a = MIN; a <= MAX; a++) {
			if ( numbersAlreadyUsed.get(a) ) {
				continue;
			}
			numbersAlreadyUsed.set(a);
			for (int b = MIN; b <= MAX; b++) {
				if ( numbersAlreadyUsed.get(b) ) {
					continue;
				}
				numbersAlreadyUsed.set(b);
				for (int c = MIN; c <= MAX; c++) {
					if ( numbersAlreadyUsed.get(c) ) {
						continue;
					}
					if ( a+b+c+1+22!=SUM ) {
						continue;
					}
					numbersAlreadyUsed.set(c);
					for (int d = MIN; d <= MAX; d++) {
						if ( numbersAlreadyUsed.get(d) ) {
							continue;
						}
						numbersAlreadyUsed.set(d);
						for (int e = MIN; e <= MAX; e++) {
							if ( numbersAlreadyUsed.get(e) ) {
								continue;
							}
							numbersAlreadyUsed.set(e);
							for (int f = MIN; f <= MAX; f++) {
								if ( numbersAlreadyUsed.get(f) ) {
									continue;
								}
								if ( d+e+f+24+11!=SUM ) {
									continue;
								}
								numbersAlreadyUsed.set(f);
								for (int g = MIN; g <= MAX; g++) {
									if ( numbersAlreadyUsed.get(g) ) {
										continue;
									}
									numbersAlreadyUsed.set(g);
									for (int h = MIN; h <= MAX; h++) {
										if ( numbersAlreadyUsed.get(h) ) {
											continue;
										}
										numbersAlreadyUsed.set(h);
										for (int i = MIN; i <= MAX; i++) {
											if ( numbersAlreadyUsed.get(i) ) {
												continue;
											}
											numbersAlreadyUsed.set(i);
											for (int k = MIN; k <= MAX; k++) {
												if ( numbersAlreadyUsed.get(k) ) {
													continue;
												}
												if ( g+h+i+k+5!=SUM ) {
													continue;
												}
												numbersAlreadyUsed.set(k);
												for (int l = MIN; l <= MAX; l++) {
													if ( numbersAlreadyUsed.get(l) ) {
														continue;
													}
													if ( d+g+l+1+19!=SUM) {
														continue;
													}
													numbersAlreadyUsed.set(l);
													for (int m = MIN; m <= MAX; m++) {
														if ( numbersAlreadyUsed.get(m) ) {
															continue;
														}
														numbersAlreadyUsed.set(m);
														for (int n = MIN; n <= MAX; n++) {
															if ( numbersAlreadyUsed.get(n) ) {
																continue;
															}
															if ( l+m+n+3+17!=SUM ||
																 f+k+n+22+13 != SUM) {
																continue;
															}
															numbersAlreadyUsed.set(n);
															for (int o = MIN; o <= MAX; o++) {
																if ( numbersAlreadyUsed.get(o) ) {
																	continue;
																}
																if ( a+h+o+24+3!=SUM) {
																	continue;
																}
																numbersAlreadyUsed.set(o);
																for (int p = MIN; p <= MAX; p++) {
																	if ( numbersAlreadyUsed.get(p) ) {
																		continue;
																	}
																	if ( b+e+m+p+5!=SUM) {
																		continue;
																	}
																	numbersAlreadyUsed.set(p);
																	for (int r = MIN; r <= MAX; r++) {
																		if ( numbersAlreadyUsed.get(r) ) {
																			continue;
																		}

																		if ( o+p+r+19+13==SUM &&
																			 c+i+r+11+17==SUM ) { 
																			 //(a*k == 40 ||
																			  // a*k == 42) ) {
																			square[0][1] = a;
																			square[0][2] = b;
																			square[0][3] = c;
																			square[1][0] = d;
																			square[1][2] = e;
																			square[1][4] = f;
																			square[2][0] = g;
																			square[2][1] = h;
																			square[2][3] = i;
																			square[2][4] = k;
																			square[3][0] = l;
																			square[3][2] = m;
																			square[3][4] = n;
																			square[4][1] = o;
																			square[4][2] = p;
																			square[4][3] = r;

																			System.out.println("Solution #" + ++numberSolutions);
																			debug();
																			System.out.println();
																			System.out.println(String.format("N 52° %2d.%3d E 009° %2d.%3d",(h - e + c),( i*m + 3*n*p + d*g - m), (a*k),( b*f + 5*l + n)));
																			System.out.println();
																		}
																	}
																	numbersAlreadyUsed.clear(p);
																}
																numbersAlreadyUsed.clear(o);
															}
															numbersAlreadyUsed.clear(n);
														}
														numbersAlreadyUsed.clear(m);
													}
													numbersAlreadyUsed.clear(l);
												}
												numbersAlreadyUsed.clear(k);
											}
											numbersAlreadyUsed.clear(i);
										}
										numbersAlreadyUsed.clear(h);
									} 
									numbersAlreadyUsed.clear(g);
								}
								numbersAlreadyUsed.clear(f);
							} 
							numbersAlreadyUsed.clear(e);
						}
						numbersAlreadyUsed.clear(d);
					} 
					numbersAlreadyUsed.clear(c);
				} 
				numbersAlreadyUsed.clear(b);
			} 
			numbersAlreadyUsed.clear(a);
		}
	}

}
