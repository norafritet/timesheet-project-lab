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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author user
 */
@Service("activityManager")
public class ActivityManagerImpl extends GenericManagerImpl<Activity,Long> implements ActivityManager{
    
    ActivityDao activityDao;
    
    public ActivityManagerImpl() {};
    
    @Autowired
    public ActivityManagerImpl(ActivityDao activityDao) {
        super(activityDao);
        this.activityDao = activityDao;
    }
     
    @Override
    public List<Activity> findByActivityWeek(Long userId, Date firstDate, Date endDate) {
        try{
        System.out.println("testing = "+userId+" "+firstDate+" "+endDate);
        
        List a =  activityDao.findByActivityWeek(userId, firstDate, endDate);
        }
        catch(Exception e){
            System.out.println("error = "+e);
        }
        List b = null;
        return b;
    }

    @Override
    public JRDataSource getJRDataSourceActivity(Long id, Date firstDate, Date endDate) {
        return new JREmptyDataSource(1);
    }

}