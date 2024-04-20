package logic;

import org.junit.Test;

import javax.mail.MessagingException;
import log.Log;

import static org.junit.Assert.*;

public class emailControlTest {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(emailControlTest.class);

    @Test
    public void testSendEmail() {
        emailControl emailControl = new emailControl();

        String toEmail = "daur0704@outlook.com";
        String subject = "Prueba de correo";
        String body = "Hola, esto es un correo de prueba.";

        try {
            emailControl.sendEmail(toEmail, subject, body);
        } catch (MessagingException e) {
            LOG.error(e.getMessage());
            fail("No se esperaba una excepción");
        }
    }

}