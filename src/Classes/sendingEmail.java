/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author hassan
 */
public class sendingEmail {
     public static void sendMail(String recipient, String fileName) throws Exception{
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String sendingAccountEmail = "electronicsphonesnrepairs@gmail.com";
        String sendingAccountPassword = "SFEcw1848";
        
        Session session = Session.getInstance(properties, new Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(sendingAccountEmail,sendingAccountPassword);
           }
        });
            
        Message message = prepareMessage(session, sendingAccountEmail, recipient, fileName);
        Transport.send(message);
        System.out.println("Message has been sent!!!!");
    }

    private static Message prepareMessage(Session session, String sendingAccountEmail, String recipient, String fileName) {
         try {
             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(sendingAccountEmail));
             message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
             message.setSubject("Elecetronics Phones & Repairs Invoice");
             //message.setText("Please see attached file to view your receipt for your latest order.");
             
             BodyPart messageBody = new MimeBodyPart();
             messageBody.setText("Please see attached file to view your receipt for your latest order at Electronics Phones & Repairs.");
             
             
             BodyPart messageBodyEmail = new MimeBodyPart();
             DataSource source = new FileDataSource(fileName);
             messageBodyEmail.setDataHandler(new DataHandler(source));
             
             messageBodyEmail.setFileName(fileName);
             
             //message.setText("Please see attached file to view your receipt for your latest order.");
             Multipart allPart = new MimeMultipart();
             allPart.addBodyPart(messageBody);
             allPart.addBodyPart(messageBodyEmail);
             
             message.setContent(allPart);
             
             return message;
             
         } catch (Exception ex) {
             Logger.getLogger(sendingEmail.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }
}
