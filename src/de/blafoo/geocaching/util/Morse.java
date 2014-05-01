package de.blafoo.geocaching.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class Morse {

	public final static int LOW = 0;
	public final static int HIGH = 1;
	
	public static final String PUNKT = ".";
	public static final String STRICH = "-";
	public static final String PAUSE = " ";
	public static final String SPACE = " ";
	public static final String UNKNOWN = "?";
	
	private static Map<Character, String> alphabetEncode = new HashMap<Character, String>();
	private static Map<String, Character> alphabetDecode = new HashMap<String, Character>();
	
	static {
		alphabetEncode.put('0', "-----");
		alphabetEncode.put('1', ".----");
		alphabetEncode.put('2', "..---");
		alphabetEncode.put('3', "...--");
		alphabetEncode.put('4', "....-");
		alphabetEncode.put('5', ".....");
		alphabetEncode.put('6', "-....");
		alphabetEncode.put('7', "--...");
		alphabetEncode.put('8', "---..");
		alphabetEncode.put('9', "----.");
		alphabetEncode.put('A',".-");
		alphabetEncode.put('B',"-...");
		alphabetEncode.put('C',"-.-.");
		alphabetEncode.put('D',"-..");
		alphabetEncode.put('E',".");
		alphabetEncode.put('F',"..-.");
		alphabetEncode.put('G',"--.");
		alphabetEncode.put('H',"....");
		alphabetEncode.put('I',"..");
		alphabetEncode.put('J',".---");
		alphabetEncode.put('K',"-.-");
		alphabetEncode.put('L',".-..");
		alphabetEncode.put('M',"--");
		alphabetEncode.put('N',"-.");
		alphabetEncode.put('O',"---");
		alphabetEncode.put('P',".--.");
		alphabetEncode.put('Q',"--.-");
		alphabetEncode.put('R',".-.");
		alphabetEncode.put('S',"...");
		alphabetEncode.put('T',"-");
		alphabetEncode.put('U',"..-");
		alphabetEncode.put('V',"...-");
		alphabetEncode.put('W',".--");
		alphabetEncode.put('X',".--");
		alphabetEncode.put('Y',"-.--");
		alphabetEncode.put('Z',"--..");
		alphabetEncode.put(' ',"−···−");
		alphabetEncode.put('+',".-.-.");
		alphabetEncode.put('-',"-....-");
		alphabetEncode.put('.',".-.-.-");
		alphabetEncode.put(',',"--..--");
		alphabetEncode.put(':',"---...");
		alphabetEncode.put(';',"-.-.-.");
		//alphabetEncode.put('<',"-.-.-");		// <Spruchanfang>
		//alphabetEncode.put('>',".-.-.");		// <Spruchende>
		
		for (Character plain: alphabetEncode.keySet()) {
			String cipher = alphabetEncode.get(plain);
			alphabetDecode.put(cipher, plain);
		}
	}

	public String encode(final String plain) {
		String cipher = "";
		String plainUpper = plain.toUpperCase();
		for (int i = 0; i < plain.length(); i++) {
			String c = alphabetEncode.get(plainUpper.charAt(i));
			if ( StringUtils.isEmpty(c) ) {
				cipher += UNKNOWN + SPACE;
			} else {
				cipher += c + SPACE;
			}
		}
		return StringUtils.removeEnd(cipher, SPACE.toString());
	}
	
	public String decode(final String cipher) {
		String plain = "";
		for (String c : cipher.split(PAUSE.toString())) {
			Character p = alphabetDecode.get(c);
			if ( null == p ) {
				plain += UNKNOWN;
			} else {
				plain += p;
			}
		}
		return plain;
	}
	
	/**
	 * Wird verwendet von K130
	 * 
	 * @param colors
	 * @param minLength
	 * @return
	 */
	public String convert(int[] colors, int minLength) {
		
		String result = "";
		
		Integer prev = LOW;
		int length = 0;
		
		for ( int actual : colors ) {
			if ( actual != prev ) {
				if ( actual == LOW ) {
					// Wechsel HIGH->LOW
					
					// Auswertung für HIGH
					if ( length >= minLength ) {
						result += Morse.STRICH;
					} else {
						result += Morse.PUNKT;
					}
					
					// Initialiserung für nächste Auswertung
					length = 1;
					prev = LOW;
				} else {
					// Wechsel LOW->HIGH
					
					// Auswertung für LOW
					if ( length >= minLength ) {
						result += Morse.PAUSE;
					} else {
						//
					}
					
					// Initialiserung für nächste Auswertung
					length = 0;
					prev = HIGH;
				}
			} else {
				// kein Wechsel, weier zählen
				length++;
			}
		}
		
		return result;
		
	}

}
