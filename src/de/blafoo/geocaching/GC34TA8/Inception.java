package de.blafoo.geocaching.GC34TA8;

import java.io.IOException;

import de.blafoo.util.image.GIF;

public class Inception {

	public static void main(String[] args) throws IOException {
		GIF gif = new GIF("res\\zielperson.gif");
		
		gif.isValid();
		gif.debug();

	}

}
