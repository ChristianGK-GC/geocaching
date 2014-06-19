package de.blafoo.util.javamail;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


/**
 * http://www.itblogging.de/java/email-versenden-mit-javamail/
 * http://www.java-forum.org/java-basics-anfaenger-themen/158615-javamail-funktioniert-immer.html
 * 
 */
public class MailTest {

	public static void main(String[] args) throws AddressException, MessagingException, IOException
    {
		InputStream input = new FileInputStream("config.properties");
		Properties prop = new Properties();
		prop.load(input);
		String from = prop.getProperty("from");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
			
        String recipient = "test@blafoo.de";
        String subject = "javamail Test";
        String text = "github";
 
        Mail.send( "mail.gmx.net", 587, recipient, from, subject, text, user, password);
    }

}
