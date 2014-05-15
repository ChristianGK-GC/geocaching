package de.blafoo.geocaching.GC3FF37;


public class AgentenMitBiss {
	
	final static int A = 'A';
	final static int Z = 'Z';
	final static int SIZE_ALPHABET = 26;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		String key =  "PizzaProvocateurSpaghettiSpidermanCodierterCocktailCoolshotDosenravioliDurchgeknalltHähnchenHackermethodikGyrosGenialGezocktFiletsFreestyleSalatSpecialsleepersMedaillonMissionMultiMaxiMegaInsolubleDessertDesolateDesaster";
		key =  "PPSSCCCoolshotDosenravioliDurchgeknalltHähnchenHackermethodikGyrosGenialGezocktFiletsFreestyleSalatSpecialsleepersMedaillonMissionMultiMaxiMegaInsolubleDessertDesolateDesaster";
		String text = "JAWABTAVBF,UYDZQQEPQKINVPVWWWSIOSGLEIYRVBLIEPPECJIQFGQBARKAVYZJRDEMJLSAGHLEAAVLUAATEZGWOXBFCEMSREYGGHGZTPAYIFGIZLDRYWPVZWIHFFEYYQKRUTXWKGVHVHZXGYCBEOTUNPTTCWYAOMHQBGAJW:VZUEKQCKKTITLRFBBYTNKVKQNNJSFXREFZAHDBHFCVL2HKFSPCG";

		//text = "AXFJ"; // ZWEI mit AAAA
		//key = "AAAA";

		String keyUpper = key.toUpperCase();
		
		for ( int i=0; i < text.length(); i++) {
			char k = keyUpper.charAt(i);
			char t = text.charAt(i);
			
			char xor = toChar(toByte(k) ^ toByte(t));
			char sub = rotate(t, -toByte(k));

			System.out.println(xor + " " + sub);
		}
	}
	
	/**
	 * Converts an uppercase character into his corresponding number
	 * @param c A to Z
	 * @return 0 to 25
	 */
	static int toByte(char c) {
		return c - A;
	}
	
	/**
	 * Converts a number to the corresponding uppercase character
	 * @param i 0 to 25
	 * @return A to Z
	 */
	static char toChar(int i) {
		return (char) (i + A);
	}
	
	/**
	 * @param origin Uppercase character
	 * @param count
	 * @return
	 */
	static char rotate(char origin, int count) {
		if ( count >= 0 ) {
			return toChar(toByte(origin) + count % SIZE_ALPHABET);
		} else {
			int temp = toByte(origin) + count;
			if ( temp < 0) {
				temp = SIZE_ALPHABET + temp;
			}
			return toChar(temp);
		}
	}
	
	static void testRotate() {
		System.out.println(rotate('A',1));
		System.out.println(rotate('A', -1));
	}

}
