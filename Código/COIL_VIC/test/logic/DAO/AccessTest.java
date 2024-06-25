package logic.DAO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.Access;
import logic.FieldValidator;

public class AccessTest {
    
    @Test
    public void userGeneratorTestSuccess(){
        String fullname = "Marla Aguilar";
        int expectedResult = 17;
        Access access = new Access();
        String userName = access.userGenerator(fullname);
        int result = userName.length();
        assertEquals(expectedResult, result);

    }

    @Test 
    public void passwordGeneratorTestSuccess(){
        boolean expectedResult = true;
        Access access = new Access();
        String password = access.passwordGenerator(8);
        boolean result = FieldValidator.validatePassword(password);
        assertEquals(expectedResult, result);

    }

}
