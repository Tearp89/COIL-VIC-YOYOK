package DAO;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.classes.Activity;
import logic.DAO.ActivityDAO;

/**
 *
 * @author isabe
 */
public class ActivityDAOTest {

    @Test
    public void addActivityTestSuccess() {
        Activity activity = new Activity();
        ActivityDAO activityDAO = new ActivityDAO();
        activity.setTitle("NombreActividadTest");
        activity.setDescription("DescripcionTest");
        activity.setType("Rompe Hielo");
        activity.setWeek("1");

        int rowsAffected = activityDAO.addActivity(activity);
        assertEquals(1, rowsAffected);
    }

    @Test
    public void deleteActivityTestSuccess() {
        Activity activity = new Activity();
        ActivityDAO activityDAO = new ActivityDAO();
        activity.setActivityId(17);

        int rowsAffected = activityDAO.deleteActivity(activity);
        assertEquals(1, rowsAffected);
    }

    @Test 
    public void updateActivityTestSuccess() {
        Activity activity = new Activity();
        ActivityDAO activityDAO = new ActivityDAO();
        activity.setTitle("NuevoNombreActividadTest");
        activity.setDescription("NuevaDescripcionTest");
        activity.setType("NuevoNombreResponsable");
        activity.setActivityId(12);
        activity.setWeek("2");
        
        int rowsAffected = activityDAO.updateActivity(activity);
        assertEquals(1, rowsAffected);
    }

    @Test
    public void assignActivityToCollaborationTestSuccess(){
        int collaborationId = 46;
        int activityId = 12;
        ActivityDAO activityDAO = new ActivityDAO();
        int expectedResult = 1;
        int result = activityDAO.assignActivityToCollaboration(collaborationId, activityId);
        assertEquals(expectedResult, result);

    }

    @Test
    public void getActivitiesByCollaborationIdAndWeekTestSuccess(){
        int collaborationId = 46;
        int activityId = 1;
        int expectedResult = 1;
        ActivityDAO activityDAO = new ActivityDAO();
        int result = activityDAO.getActivitiesByCollaborationAndWeek(collaborationId, "1").size();
        assertEquals(expectedResult, result);


    }

    @Test
    public void isActivityAssignedToWeekTestSuccesfull(){
        int idColaboración = 46;
        String week = "2";
        boolean expectedResult = true;
        ActivityDAO activityDAO = new ActivityDAO();
        boolean result = activityDAO.isActivityAssignedInWeek(idColaboración, week);
        assertEquals(expectedResult, result);

    }

    @Test
    public void isActivityTypeAssignedTestSuccess(){
        int idColaboración = 46;
        String type = "Rompe hielo";
        boolean expectedResult = true;
        ActivityDAO activityDAO = new ActivityDAO();
        boolean result = activityDAO.isActivityTypeAssigned(idColaboración, type);
        assertEquals(expectedResult, result);

    }

    @Test
    public void getActivityTypeByIdTestSuccess(){
        int activityId = 13;
        String expectedResult = "Proyecto COIL";
        ActivityDAO activityDAO = new ActivityDAO();
        String result = activityDAO.getActivityTypeById(activityId);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActivityWeekById(){
        int activityId = 12;
        String expectedResult = "2";
        ActivityDAO activityDAO = new ActivityDAO();
        String result = activityDAO.getActivityWeekById(activityId);
        assertEquals(expectedResult, result);
    }




}