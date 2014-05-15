package de.blafoo.geocaching.util;

public class Analyser extends Filter {
	
	private int r;
	private int g;
	private int b;
	
	public Analyser(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	

	@Override
	protected int applyFilter(int rgbIn) {
		int rgb = rgbIn & 0xFFFFFF;
		int rIn = rgb & 0xFF0000;
		int gIn = rgb & 0x00FF00;
		int bIn = rgb & 0x0000FF;
		if ( rIn >= r - 0x050000 && rIn <= r + 0x050000) {
			System.out.println("Found R");
			return rIn;
		}
		if ( gIn >= g - 0x000500 && gIn <= g + 0x000500) {
			System.out.println("Found G");
			return gIn;
		}
		if ( bIn >= b - 0x000005 && bIn <= b + 0x000005) {
			System.out.println("Found B");
			return bIn;
		}
		return 0x00;
	}

}
