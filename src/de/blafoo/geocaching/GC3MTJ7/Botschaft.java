package de.blafoo.geocaching.GC3MTJ7;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Botschaft {

	public static void main(String[] args) throws IOException {
		
		BufferedImage iin = ImageIO.read(new File("D:\\Daten\\Develop\\Eclipse\\GC3MTJ7 Waldmeister\\GC3MTJ7 teufelskueche.bmp"));
		
		ArrayList<Character> matrix = new ArrayList<Character>();
		
		// Decoding
		for (int x=0; x < 70; x++) {
			int color = iin.getRGB(x, 0);
			
			for (int i=23; i >=0; i--) {
				matrix.add( (color & (1 << i )) > 0 ? new Character('*') : new Character(' ') ); 
			}
		}
		
		// Output
		int cols = 1;
		int row = 1;
		System.out.print(String.format("%2d ", row));
		for (Character c : matrix) {
			System.out.print(c);
			cols++;
			if ( cols == 24 ) {
				System.out.println();
				row++;
				System.out.print(String.format("%2d ", row));
				cols = 1;
			}
		}
    }
}
