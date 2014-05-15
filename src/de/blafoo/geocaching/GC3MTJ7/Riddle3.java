package de.blafoo.geocaching.GC3MTJ7;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Riddle3 {

	public static void main(String[] args) throws IOException {
		
		BufferedImage iin = ImageIO.read(new File("C:\\Users\\blafoo\\Desktop\\riddle_three_with_hint.bmp"));
		File outputfile = new File("C:\\Users\\blafoo\\Desktop\\riddle_three_with_hint2.bmp");
		
		for (int y=0; y < iin.getHeight(); y++) {
			for (int x=0; x < iin.getWidth(); x++) {
				// int i = iin.getRGB(x, y) & 0x010101;
				int i = iin.getRGB(x, y) & 0x020202;
				if ( i == 0) {
					i = 0xFFFFFF;
				}
				iin.setRGB(x, y, i);
			}
		}
		ImageIO.write(iin, "bmp", outputfile);
    }
}
