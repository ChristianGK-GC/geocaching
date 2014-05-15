package de.blafoo.geocaching.GC2X4XP;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HarteNussTest {
	
	public static final Col COL6 = new Col(37, 7,1,1,1,1,1,1,1,1,1,1,1,7);
	
	@Test
	public void testIt() {
		final int dimX = 5;
		final int dimY = 5;
		
		Board board = new Board(dimX, dimY);
		
		board.setRows(
				new Row(dimX, 1,1),
				new Row(dimX),
				new Row(dimX, 1),
				new Row(dimX),
				new Row(dimX, 1,1)
		);
		
		board.setCols(
				new Col(dimY, 1,1),
				new Col(dimY),
				new Col(dimY),
				new Col(dimY, 1),
				new Col(dimY, 1,1)
		);
		
		board.findSolutions();
	}

}
