package de.blafoo.geocaching.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageExtractor {

	/**
	 * extract(new File("H:\\temp\\listing_zeitungstext.jpg"), new File("H:\\temp\\test1.png"), 0x000001);
	 * 
	 * @param in
	 * @param out
	 * @param mask
	 * @throws IOException
	 */
	public static void extract(File in, File out, int mask) throws IOException {
		
		BufferedImage iin = ImageIO.read(in);
		
		for (int y=0; y < iin.getHeight(); y++) {
			for (int x=0; x < iin.getWidth(); x++) {
				int i = iin.getRGB(x, y) & mask;
				if ( i == 0) {
					i = 0xFFFFFF;
				}
				iin.setRGB(x, y, i);
			}
		}
		ImageIO.write(iin, "png", out);
		
	}
	
}


