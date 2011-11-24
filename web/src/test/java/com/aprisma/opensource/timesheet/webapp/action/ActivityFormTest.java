/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;


import com.aprisma.opensource.timesheet.model.Activity;
import java.sql.Time;
import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class ActivityFormTest {
    
    public ActivityFormTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /*@Test
    public void save_InputActivity_ReturnsMainMenu() throws Exception{
        ActivityForm form = new ActivityForm();
        Activity activity = new Activity();
        activity.setId("00000000");
        
        Date date = new Date(1);
        form.setActivityDate(date);
        
        Time time = new Time(1);
        form.setTimeFrom(time);
        form.setTimeTo(time);

        form.setActivity(activity);
        
        assertEquals("mainMenu", form.save() );

    }*/
    
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
        ActivityForm form = new ActivityForm();
        
        Time time = new Time(1);
        
        form.setTimeFrom(time);
        
        assertEquals(time, form.getTimeFrom());
    }
    @Test
    public void setTimeFrom_Time_activity$activityTimeFromIsNull(){
        ActivityForm form = new ActivityForm();

        Time time = null;

        form.setTimeFrom(time);

        assertNull(form.getTimeFrom());
    }
    
    
    //test for timeTo
    @Test 
    public void setTimeTo_Time_activity$activityTimeToNotSameAsInput(){
        ActivityForm form = new ActivityForm();
        
        Time time = new Time(1);
        
        form.setTimeTo(time);
        
        assertEquals(time, form.getTimeTo());
    }
    @Test
    public void setTimeTo_Time_activity$activityTimeToIsNull(){
        ActivityForm form = new ActivityForm();

        Time time = null;

        form.setTimeTo(time);

        assertNull(form.getTimeTo());
    }
}
