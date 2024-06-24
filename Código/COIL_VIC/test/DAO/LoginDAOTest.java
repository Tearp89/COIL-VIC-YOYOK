package DAO;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import logic.DAO.LoginDAO;

public class LoginDAOTest {

    @Test
    public void testValidateUserValidCredentials() {
        LoginDAO adminValidation = new LoginDAO();
        String validUsername = "rPerez";
        String validPassword = "ContraseñaTest";
        assertTrue(adminValidation.validateAdmin(validUsername, validPassword));
    }

    @Test
    public void testValidateUserInvalidCredentials() {
        LoginDAO adminValidation = new LoginDAO();
        String invalidUsername = "juan.admin";
        String invalidPassword = "contraseña_invalida";
        assertFalse(adminValidation.validateAdmin(invalidUsername, invalidPassword));
    }

}
