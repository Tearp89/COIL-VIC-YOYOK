package logic;

import org.junit.Test;
import logic.EmailControl;
import javax.mail.MessagingException;
import log.Log;

import static org.junit.Assert.*;

public class EmailControlTest {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(EmailControlTest.class);

    @Test
    public void testSendEmail() {
        EmailControl emailControl = new EmailControl();

        String toEmail = "daur0704@outlook.com";
        String subject = "Prueba de correo";
        String body = "Hola, esto es un correo de prueba.";

        try {
            emailControl.sendEmail(toEmail, subject, body);
        } catch (MessagingException e) {
            LOG.warn(e.getMessage());
            fail("No se esperaba una excepción");
        }
    }

}