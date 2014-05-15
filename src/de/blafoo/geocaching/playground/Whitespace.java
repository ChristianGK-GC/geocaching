package de.blafoo.geocaching.playground;

/**
 * http://compsoc.dur.ac.uk/whitespace/download.php 					Perl Interpreter
 * http://www.sable.mcgill.ca/JBCO/										Obfuscator
 * http://de.wikipedia.org/wiki/Whitespace_%28Programmiersprache%29		HelloWorld in WS
 * http://compsoc.dur.ac.uk/whitespace/tutorial.php						WS Tutorial
 *
 */
public class Whitespace {

	private static final String crypt = 
			"lllullltlltttluttlllltulllttltltuttlllltlulllttlltluttllllttulllttlltluttlllltll"+
			"ulllttltltuttlllltltulllttllltuttllllttlulllttlltluttlllltttulllttllttuttlllltll"+
			"lullltllltltuttlllltlltullltttlltuttlllltltlulllttltlluttlllltlttulllttlllluttll"+
			"llttllulllttllltuttllllttltulllttlltluttlllltttlulllttllttuttllllluulllltululttt"+
			"lulutlltlutullllltutlllululltuullltluuuu";

	/** Bei Aufruf der Anwendung werden KOs ausgegeben (die falschen !)
	 *  Wird die Anwendung mit Parametern aufgerufen, werden andere KOs (ebenfalls falsch) ausgegeben.
	 *  Die Berechnung der 2. KOs muss relativ cryptisch sein.
	 *  Die ganze Anwendung ist obfuscated. 
	 *  Die Ausgabe in beiden F�lle ist ein Whitespace Programm.
	 *  Die Ausgabe erzeugt mittels:
	 *  $ sed "s|//.*||g" < Solution2.txt | tr -d " \t\n"
	 *  $ perl whitespace.pl Solution2.ws 
	 *  das richtige Ergebnis
	 */
	public static void main(String[] args) {
		
		System.out.println("N 52 12.345");
		
		System.out.println(crypt.replace('l',' ').replace('u', '\n').replace('t', '\t'));
	}

}