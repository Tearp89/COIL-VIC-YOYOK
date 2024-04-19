/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package log;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daur0
 */
public class LogTest {
    private static final Logger LOG = Log.getLogger(LogTest.class);
    /**
     * Test of getLogger method, of class Log.
     */
    @Test
    public void testGetLogger() {
        LOG.debug("DEBUG: test");
        LOG.info("INFO: test");
        LOG.error("ERROR: test");
        LOG.warn("WARN: test");
        LOG.fatal("FATAL: test");
    }
    
}
