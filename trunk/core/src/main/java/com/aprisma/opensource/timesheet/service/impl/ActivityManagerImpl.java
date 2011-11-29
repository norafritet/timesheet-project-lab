/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service.impl;


import com.aprisma.opensource.timesheet.dao.ActivityDao;
import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import java.sql.Date;
import java.util.List;
import org.appfuse.service.impl.GenericManagerImpl;
/**
 *
 * @author user
 */

public class ActivityManagerImpl extends GenericManagerImpl<Activity,Long> implements ActivityManager{
    ActivityDao activityDao;
    
     public ActivityManagerImpl(ActivityDao activityDao) {
        super(activityDao);
        this.activityDao = activityDao;
    }
     
    @Override
    public List<Activity> findByActivityWeek(Long userId, Date firstDate, Date endDate) {
        return activityDao.findByActivityWeek(userId, firstDate, endDate);
    }

}