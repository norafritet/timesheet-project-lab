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


import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Scope("request")
@Component("activityForm")
public class ActivityForm extends BasePage implements Serializable{
    
    private GenericManager<Activity,String> activityManager;    
    private Activity activity = new Activity();
    private User user = new User();
    private String id;
    private String[] types = new String[]{"ANL","CDG","EXP","MTG","OTH","STD","TST"};
    //private String[] timeZone = TimeZone.getAvailableIDs();
    
    @Autowired
    public void setActivityManager(@Qualifier("activityManager") GenericManager<Activity, String> manager) {
        this.activityManager = manager;
    }

    //Id
    public void setId(String id){
        this.id = id;
    }
    
    
    //user
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
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
        if(activityDate != null){
            activity.setActivityDate(new java.sql.Date(activityDate.getTime()));
        }
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
        if(timeTo != null){
            System.out.println(timeTo);
            activity.setTimeTo(new java.sql.Time(timeTo.getTime()));
        }
    }
    
    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
    
    public boolean isRememberMe() {
        if (user != null && user.getId() == null) return false; // check for add()
        
        AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        SecurityContext ctx = SecurityContextHolder.getContext();

        if (ctx != null) {
            Authentication auth = ctx.getAuthentication();
            return resolver.isRememberMe(auth);
        }
        return false;
    }

    public String save() {
       boolean isNew = (activity.getId() == null);
       
        activity.setId("1212");
        activityManager.save(activity);

        
        String key = (isNew) ? "activity.added" : "activity.updated";
        addMessage(key);

        if (isNew) {
            return "mainMenu";
        } else {
            return "mainMenu";
        }
    }
    
}