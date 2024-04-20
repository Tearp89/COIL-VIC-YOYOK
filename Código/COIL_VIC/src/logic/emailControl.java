/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import log.Log;

/**
 *
 * @author daur0
 */
public class emailControl {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(emailControl.class);
    static final String USERNAME = "coilvic@outlook.com";
    static final String PASSWORD = "proyectocoil2024";
    static final String HOST = "smtp-mail.outlook.com";
    static final int PORT = 587;
    private final Properties properties;
    
    public emailControl(){
        this.properties = new Properties();
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.starttls.enable", "true");
        this.properties.put("mail.smtp.host", "smtp-mail.outlook.com");
        this.properties.put("mail.smtp.port", "587");
    }
    
    public void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
        );
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        LOG.info("Se envió un Email a: " + toEmail);
    }
}