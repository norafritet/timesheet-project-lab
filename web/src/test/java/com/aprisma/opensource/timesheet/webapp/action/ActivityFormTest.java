/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.service.ActivityManager;
import java.sql.Time;
import mockit.Delegate;
import org.appfuse.service.GenericManager;
import com.aprisma.opensource.timesheet.model.Activity;
import javax.servlet.http.HttpServletRequest;
import org.appfuse.service.UserManager;
import mockit.Mocked;
import org.junit.Before;
import org.appfuse.model.User;
import java.util.Date;
import mockit.Verifications;
import mockit.NonStrictExpectations;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Verifier;
import static org.junit.Assert.*;


/**
 *
 * @author user
 */
public class ActivityFormTest {
    
    private ActivityForm form;
    @Mocked
    private UserManager userManager;
    @Mocked
    private ActivityManager activityManager;
    @Mocked
    private HttpServletRequest httpRequest;
    private String username;
    private User user;
    private Activity activity;
    private Activity activityResult;
    
    //Set Up Data for Method Save
    @Before
    public void onSetUp() {
        username = "admin";
        form = new ActivityForm();
        activity = form.getActivity();
        form.setUserManager(userManager);
        form.setActivityManager(activityManager);
        user = new User(username);
    }
    
    
    private void context_save_InputActivity() {
        new NonStrictExpectations(form) {
            {
                form.getRequest(); result = httpRequest;
                httpRequest.getRemoteUser(); result = username;
                userManager.getUserByUsername(username);result=user;
                activityManager.save(activity);
                result = new Delegate() {
                    Activity save(Activity activity) {
                        activityResult = new Activity();
                        activityResult.setActivityCase(activity.getActivityCase());
                        activityResult.setActivityDate(activity.getActivityDate());
                        activityResult.setActivityStatus(activity.getActivityStatus());
                        activityResult.setIcenterNo(activity.getIcenterNo());
                        activityResult.setLocation(activity.getLocation());
                        activityResult.setName(activity.getName());
                        activityResult.setRemark(activity.getRemark());
                        activityResult.setTimeFrom(activity.getTimeFrom());
                        activityResult.setTimeTo(activity.getTimeTo());
                        activityResult.setType(activity.getType());
                        activityResult.setActivityUser(activity.getActivityUser());
                        activityResult.setId((long)-3);
                        
                        return activityResult;
                    }
                };
                form.addMessage("activity.added");
           }
        };
    }
    
    @Test
    public void save_InputActivity_Call$addMessage() {
        context_save_InputActivity();

        form.save();

        new Verifications() {
            {
                form.addMessage("activity.added");
            }
        };
    }

    @Test
    public void save_InputActivity_Call$getUserByUsername() {
        context_save_InputActivity();

        form.save();

        new Verifications() {

            {
                userManager.getUserByUsername(username);
            }
        };
    }

    
    @Test
    public void save_InputActivity_UserFromUserManager() {
        context_save_InputActivity();

        form.save();

        assertTrue(user==form.getActivity().getActivityUser());
    }

    
    //Verify that system can get user using "httpRequest.getRemoteUser();"
    @Test
    public void save_InputActivity_Call$getRemoteUser() {
        context_save_InputActivity();

        form.save();

        new Verifications() {

            {
                httpRequest.getRemoteUser();
            }
        };
    }
    
    
    //Verify that Activity Manager is called
    @Test
    public void save_InputActivity_Call$ActivityManager$save() {
        context_save_InputActivity();

        form.save();

        new Verifications() {

            {
                activityManager.save(activity);
            }
        };
    }
    
    //Test if User = null

    @Test
    public void save_InputActivity_UserNotNull() {

        context_save_InputActivity();

        form.save();
        
        Activity a = mockit.Deencapsulation.getField(form, "activity");
        assertNotNull(a.getActivityUser());
    }
   
    
    
    //Test Method Save
    @Test
    public void save_InputActivity_ReturnsMainMenu() {
        context_save_InputActivity();

        String result = form.save();

        assertEquals("mainMenu", result);
    }
    
  
    // test for convert date
    @Test 
    public void setActivityDate_Date_activity$activityDateNotSameAsInput(){
        ActivityForm form = new ActivityForm();
        
        Date date = new Date(1);
        
        form.setActivityDate(date);
        
        assertEquals(date, form.getActivityDate());
    }
    
    @Test 
    public void setActivityDate_Date_activity$activityDateIsNull(){
        ActivityForm form = new ActivityForm();
        
        Date date = null;
        
        form.setActivityDate(date);
        
        assertNull(form.getActivityDate());

    }
    
    //test for timeFrom
     @Test 
    public void setTimeFrom_Time_activity$activityTimeFromNotSameAsInput(){
        
        Time time = new Time(1);
        
        form.setTimeFrom(time);
        
        assertEquals(time, form.getTimeFrom());
    }
     
    @Test
    public void setTimeFrom_Time_activity$activityTimeFromIsNull(){

        Time time = null;

        form.setTimeFrom(time);

        assertNull(form.getTimeFrom());
    }
    //test for timeTo
    @Test 
    public void setTimeTo_Time_activity$activityTimeToNotSameAsInput(){
        
        Time time = new Time(1);
        
        form.setTimeTo(time);
        
        assertEquals(time, form.getTimeTo());
    }
    @Test
    public void setTimeTo_Time_activity$activityTimeToIsNull(){

        Time time = null;

        form.setTimeTo(time);

        assertNull(form.getTimeTo());
    }
}
