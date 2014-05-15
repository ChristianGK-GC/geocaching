package de.blafoo.geocaching.util;

public class ExtractFilter extends Filter {
	
	public int color;
	public int range;
	public int max;
	public int min;
	
	
	public ExtractFilter(int color, int range, int max, int min) {
		this.color = color;
		this.range = range;
		this.max = max;
		this.min = min;
	}

	@Override
	protected int applyFilter(int rgb) {
		
		if ( (rgb & 0x00FFFFFF ) == color ) {
			return max;
		}
		return min;
	}

}
