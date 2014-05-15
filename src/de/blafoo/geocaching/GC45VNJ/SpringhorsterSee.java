package de.blafoo.geocaching.GC45VNJ;

import java.io.IOException;

import de.blafoo.geocaching.util.Analyser;
import de.blafoo.geocaching.util.Filter;

/**
 * http://coord.info/GC45VNJ
 * 
 * @author blafoo
 *
 */
public class SpringhorsterSee {

	public static void main(String[] args) throws IOException {
		
		String dateiIn = "C:\\Users\\blafoo\\Desktop\\spring_horst_sep_tol_4-5_4b_87_a5_see_230213_52_09.jpg";
		String dateiOut = "C:\\Users\\blafoo\\Desktop\\horst";
		
		Filter analyser1 = new Analyser(0xa50000, 0x8700, 0x4b);
		analyser1.filterImage(dateiIn, dateiOut + "a.bmp");
		
		/*
		Filter filter1 = new ExtractFilter(0x4b87a5, 0, 0xffffffff, 0);
		filter1.filterImage(dateiIn, dateiOut + "1.bmp");
		
		
		Filter filter2 = new ExtractFilter(0xa5874b, 0, 0xffffffff, 0);
		filter2.filterImage(dateiIn, dateiOut + "2.bmp");
		
		Filter filter2 = new MaskFilter(0xFF4f8faf);
		filter2.filterImage(dateiIn, dateiOut + "2.bmp");
		*/
	
    }
}
