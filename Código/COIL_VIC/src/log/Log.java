/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.net.SMTPAppender;

/**
 *
 * @author daur0
 */
public class Log {
    
    private static Logger LOG;
    private static final String SMTP_HOST = "smtp-mail.outlook.com";
    private static final String SMTP_PORT = "587";
    private static final String FROM_EMAIL = "coilvic@outlook.com";
    private static final String TO_EMAIL = "daur0704@outlook.com";
    
    public static Logger getLogger(Class<?> name) {
        try {
            LOG = Logger.getLogger(name);
            String logfile = "logs\\filelog.";
            Date fecha = new Date();
            
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fechaAc = formato.format(fecha);
            PatternLayout defaultLayout = new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} %-5p %c{1}:%L: %m%n");
            
            RollingFileAppender rollingFileAppender = new RollingFileAppender();
            rollingFileAppender.setFile(logfile + fechaAc + ".log", true, false, 0);
            rollingFileAppender.setMaxFileSize("10MB");
            rollingFileAppender.setMaxBackupIndex(5);
            rollingFileAppender.setLayout(defaultLayout);
            
            SMTPAppender smtpAppender = new SMTPAppender();
            smtpAppender.setSMTPHost(SMTP_HOST);
            smtpAppender.setSMTPPort(Integer.parseInt(SMTP_PORT));
            smtpAppender.setFrom(FROM_EMAIL);
            smtpAppender.setTo(TO_EMAIL);
            smtpAppender.setSMTPUsername("coilvic@outlook.com");
            smtpAppender.setSMTPPassword("proyectocoil2024");
            smtpAppender.setBufferSize(1);
            smtpAppender.setSMTPDebug(true);
            smtpAppender.setLayout(defaultLayout);
            
            LOG.removeAllAppenders();
            LOG.addAppender(rollingFileAppender);
            LOG.addAppender(smtpAppender);
            LOG.setAdditivity(true);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LOG;
    }
    
}
