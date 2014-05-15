package de.blafoo.geocaching.GC3K64D;

/**
 * GC3K64D MaglITe
 * 
 * @author blafoo
 *
 */
public class MaglITe {
	
    public static char[] e = "Wp\"+4ggzmm6 egf-%}hz)nes\"IimrlzH*Pbuvtmxh{nD xkh$mkp{|~ ecv$('6jrx/tj#C%\"'|{*a.|#E2`".toCharArray();
    
    public static void main(String[] args) {
        for (int p = 0; p < e.length; ++p) {
            e[p] -= p % 11;
            if (e[p] < 32) e[p] += 96;
        }
        System.out.println(new String(e));
    }
}
