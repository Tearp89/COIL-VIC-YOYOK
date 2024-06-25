package logic.DAO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.ActivityValidator;
import logic.classes.Activity;

public class ActivityValidatorTest {
    
    @Test
    public void validateActivityFieldsTestSuccess(){
        Activity activity = new Activity();
        activity.setWeek("2");
        activity.setTitle("Conoce a tu compañero");
        activity.setType("Rompe hielo");
        activity.setDescription("Realiza 10 preguntas a tu compañero, de esta manera podrás saber más acerca de él");
        boolean expectedResult = true;
        boolean result = ActivityValidator.validateActivityFields(activity);
        assertEquals(expectedResult, result);
    }

    




}
