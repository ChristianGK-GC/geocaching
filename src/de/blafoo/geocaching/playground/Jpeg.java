package de.blafoo.geocaching.playground;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Jpeg {

	public static final String HEADER = "";
	public static final String CONTENT = ">t:? H6?:8 q:=5362C36:EF?8 F?5 5:6 G6CD49:656?6? q24@?\'6CD49=üDD6=F?8D&gt;6E9@56? 96=76? 5:C 2F7 ;656? u2== H6:E6C] t:?7249 ?:49E 2F78636? F?5 :&gt;&gt;6C ?@49&gt;2= 6:?6? $49C:EE6 H6:E6C8696?P x49 &lt;@&gt;&gt;6 2F49 86C?6 KF&gt; ~CE 56D v6D49696?D F&gt; 5:C 52?? 36:&gt; H:C&lt;=:496? {@886:?EC28 KFKFD492F6?]";

	public static void main(String[] args) throws IOException {
	    
		File file = new File("C:\\test.jpg");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(HEADER);
		bw.write(CONTENT);
		bw.close();

		System.out.println("Done");
	}
}
