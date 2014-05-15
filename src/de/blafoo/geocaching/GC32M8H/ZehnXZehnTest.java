package de.blafoo.geocaching.GC32M8H;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ZehnXZehnTest {

	@Test
	public void testValidMoves() {

		System.out.println("(1,1)");
		for ( final Field field: ZehnXZehn.getValidMoves(1,1) ) {
			System.out.println(field.toString());
		}
		System.out.println("(8,9)");
		for ( final Field field: ZehnXZehn.getValidMoves(8,9) ) {
			System.out.println(field.toString());
		}
		System.out.println("(5,5)");
		for ( final Field field: ZehnXZehn.getValidMoves(5,5) ) {
			System.out.println(field.toString());
		}
	}

}
