package de.blafoo.geocaching.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;

public class Download {

	private Type type;
	private String proxy;
	private int port;
	
	/** CTOR to define a direct internet connection / no proxy.
	 * 
	 */
	public Download() {
		type = Type.DIRECT;
	}
	
	
	/**
	 * CTOR to define a proxy.
	 * 
	 * @param type
	 * @param proxy
	 * @param port
	 */
	public Download(Type type, String proxy, int port) {
		this.type = type;
		this.proxy = proxy;
		this.port = port;
	}

	/**
	 * 
	 * @param url URL to retrieve
	 * @param directory Directory to save the URL
	 * @throws MalformedURLException
	 */
	public void getFileForURL(final String url, final String directory) throws MalformedURLException {
		try {
			InputStream is;
			final String file = directory + "\\" + url.substring(url.lastIndexOf("/"));
			URL connection = new URL(url);
			if ( type != Type.DIRECT ) {
				 URLConnection connectionWithProxy = connection.openConnection(new Proxy(type, new InetSocketAddress(proxy, port)));
				is = connectionWithProxy.getInputStream();
			} else {
				is = connection.openStream();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(Channels.newChannel(is), 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			System.out.println("IOException for " + e.getMessage());
		}
	}

}
