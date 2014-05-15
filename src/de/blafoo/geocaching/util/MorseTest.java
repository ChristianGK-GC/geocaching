package de.blafoo.geocaching.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MorseTest {

	private Morse morse = new Morse();
	
	@Test
	public void encode() {
		Assert.assertEquals("..--- -.... ----- ...--", morse.encode("2603"));
		Assert.assertEquals("... --- ...", morse.encode("SOS"));
		Assert.assertEquals("-.. .- ... .. ... - . .. -. - . ... -", morse.encode("DasisteinTest"));

	}
	
	@Test
	public void decode() {
		Assert.assertEquals("2603", morse.decode("..--- -.... ----- ...--"));
		Assert.assertEquals("SOS", morse.decode("... --- ..."));
		Assert.assertEquals("DASISTEINTEST", morse.decode("-.. .- ... .. ... - . .. -. - . ... -"));
	}
	
	@Test 
	public void analyse() {
		int[] values = new int[]{0,0,0,1,1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,0};
		Assert.assertEquals("-..", morse.convert(values,5));
	}

}
