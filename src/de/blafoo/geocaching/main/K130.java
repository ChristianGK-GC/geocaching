package de.blafoo.geocaching.main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.blafoo.geocaching.util.Morse;

/**
 * http://coord.info/GC4XEPJ
 */
public class K130 {
	
	public static void main(String[] args) {
		
		Morse morse = new Morse();
		
		System.out.println("K130-Start");
		// 1. Bilder analysieren
		int[] colorValues = analyse(1, 3523, "C:\\temp\\k130\\scene0", ".png");
		// 2. Morse produzieren
		String code = morse.convert(colorValues, 5);
		System.out.println("Code="+code);
		// 3. Morse dekodieren
		
		String result = morse.decode(code);
		System.out.println("Ergebnis="+result);
		System.out.println("K130-Ende");
		
	}
	
	private static int[] analyse(int start, int ende, String prefix, String suffix) {

		int[] result = new int[ende-start+1];
		
		try {
		
			for (int i=start; i <= ende; i++) {
				String dateiname = prefix + String.format("%04d", i) + suffix;
				BufferedImage iin = ImageIO.read(new File(dateiname));
				if ( iin != null ) {
					int color = iin.getRGB(240,180) & 0x00FF0000;
					System.out.println(dateiname + ": " + String.format("%06x", color));
					result[i-start] = (color > 0xA00000 ? Morse.HIGH : Morse.LOW);
				}
			}
		} catch (IOException e) {
		}
		
		return result;
	
	}

}
