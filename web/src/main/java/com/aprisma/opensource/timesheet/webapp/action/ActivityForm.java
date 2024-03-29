/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;

/**
 *
 * @author user
 */


import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import java.util.Date;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;

public class ActivityForm extends BasePage{

    private ActivityManager activityManager;
    
    private Activity activity = new Activity();
    private String[] types = new String[]{"","ANL","CDG","EXP","MTG","OTH","STD","TST"};
    
   
    public void setActivityManager(ActivityManager manager) {
        this.activityManager = manager;
    }
    
    //activity
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    
    public Date getActivityDate() 
    {
        return activity.getActivityDate();
    }

    public void setActivityDate(Date activityDate) {
        if(activityDate != null)
            activity.setActivityDate(new java.sql.Date(activityDate.getTime()));
    }
    
    public Date getTimeFrom() 
    {
        return activity.getTimeFrom();
    }

    public void setTimeFrom(Date timeFrom) {
        if(timeFrom != null){
            activity.setTimeFrom(new java.sql.Time(timeFrom.getTime()));
        }
    }
    public Date getTimeTo() 
    {
        return activity.getTimeTo();
    }

    public void setTimeTo(Date timeTo) {
        if(timeTo != null)
            activity.setTimeTo(new java.sql.Time(timeTo.getTime()));
    }
    
    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String save() {
       
       String username = getRequest().getRemoteUser();
       User user = userManager.getUserByUsername(username);
       activity.setActivityUser(user);
       activity = activityManager.save(activity);      
       addMessage("activity.added");

       return "mainMenu";
    }
    
}
