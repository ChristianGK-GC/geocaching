package de.blafoo.geocaching.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Filter {

	public void filterImage(String dateiIn, String dateiOut) throws IOException {

		BufferedImage iin = ImageIO.read(new File(dateiIn));
		File outputfile = new File(dateiOut);

		for (int y = 0; y < iin.getHeight(); y++) {
			for (int x = 0; x < iin.getWidth(); x++) {
				iin.setRGB(x, y, applyFilter(iin.getRGB(x, y)));
			}
		}
		ImageIO.write(iin, "bmp", outputfile);
	}
	
	protected abstract int applyFilter(int rgb);

}
