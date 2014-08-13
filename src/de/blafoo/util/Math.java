package de.blafoo.util;

import java.util.Random;

public class Math {
	
	private static final Random rand = new Random();
	
	public static int randInt(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }

}
