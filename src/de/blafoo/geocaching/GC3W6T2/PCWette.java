/**
 * 
 */
package de.blafoo.geocaching.GC3W6T2;

import java.net.MalformedURLException;
import java.net.Proxy.Type;

import de.blafoo.geocaching.util.Constants;
import de.blafoo.geocaching.util.Download;

/**
 * http://coord.info/GC3W6T2 PC Wette
 * 
 * @author blafoo
 *
 */
public class PCWette {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {

			Download http = new Download(Type.HTTP, "149.213.205.182", 3128);

			System.out.println("Testing - get");

			// http.getFileForURL("http://imgcdn.geocaching.com/avatar/816ea7ab-8878-4e22-8097-ba18294ac8d9.png", Constants.DESTDIR, Type.HTTP);


			http.getFileForURL("http://imageshack.us/scaled/landing/339/backgroundjl.jpg", Constants.DESTDIR);

			http.getFileForURL("http://imageshack.us/scaled/landing/203/78601118.jpg",	Constants.DESTDIR);

			System.out.println("Testing - get");

		}



}
