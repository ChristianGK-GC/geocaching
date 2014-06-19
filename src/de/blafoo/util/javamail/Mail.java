package de.blafoo.util.javamail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {

    public static void send(final String smtpHost, final int port, final String to, final String from, final String subject,
            final String text, final String user, final String password) throws AddressException, MessagingException
    {
        Properties properties = System.getProperties();
 
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", String.valueOf(port));
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.tls", "true");
        // properties.setProperty("mail.debug", "true");
        
        javax.mail.Authenticator auth = new javax.mail.Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
            	return new PasswordAuthentication(user, password);
            }
        };

        Session session = Session.getDefaultInstance(properties, auth);
 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        msg.setSubject(subject);
        msg.setText(text);
         
        Transport.send(msg);
    }

}
