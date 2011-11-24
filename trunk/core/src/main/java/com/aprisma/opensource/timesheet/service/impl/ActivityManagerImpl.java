/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service.impl;


import com.aprisma.opensource.timesheet.dao.ActivityDao;
import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import java.util.List;
import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author user
 */

@Service("activityManager")
public class ActivityManagerImpl extends GenericManagerImpl<Activity,String> implements ActivityManager{
    ActivityDao activityDao;
    
    @Autowired
     public ActivityManagerImpl(ActivityDao activityDao) {
        super(activityDao);
        this.activityDao = activityDao;
    }

    @Override
    public List<Activity> findByActivityUser(String activityUser) {
        return activityDao.findByActivityUser(activityUser);
    }

}