/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service.impl;

import com.aprisma.opensource.timesheet.dao.ActivityDao;
import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service("activityManager")
public class ActivityManagerImpl extends GenericManagerImpl<Activity, Long> implements ActivityManager {

    protected ActivityDao activityDao;

    public ActivityManagerImpl() {
    }

    ;
    
    @Autowired
    public ActivityManagerImpl(ActivityDao activityDao) {
        super(activityDao);
        this.activityDao = activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        super.dao = this.activityDao = activityDao;
    }

    //@Override
    public List<Activity> findByActivityWeek(Long userId, Date firstDate, Date endDate) {
		return activityDao.findByActivityWeek(userId, firstDate, endDate);
    }
    private Map<String, ?> convertActivityToMap(Activity activity) {

        Map<String, Object> mapReport = new HashMap();
        mapReport.put("date_activity", activity.getActivityDate());
        mapReport.put("time_from", activity.getActivityDate());
        mapReport.put("time_to", activity.getActivityDate());
        mapReport.put("code", activity.getType());
        mapReport.put("activities", activity.getName());
        mapReport.put("case", activity.getActivityCase());
        mapReport.put("i_center_no", activity.getIcenterNo());
        mapReport.put("status", activity.getActivityStatus());
        mapReport.put("location", activity.getLocation());
        mapReport.put("remark", activity.getRemark());
        return mapReport;

    }

    @Override
    public JRDataSource getJRDataSourceActivity(Long userId, Date firstDate, Date endDate) {

        List<Activity> activityList = activityDao.findByActivityWeek(userId, firstDate, endDate);
        List<Map<String, ?>> listOneDay, listAllDays = new ArrayList();
        java.sql.Date currentDate;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(firstDate);

        currentDate = new java.sql.Date(calendar.getTime().getTime());

        listOneDay = new ArrayList<Map<String, ?>>();
        Map mapReport;
        Activity activity;
        boolean containtActivity = false;
        Iterator<Activity> it = activityList.iterator();
        if (it.hasNext()) {
            activity = it.next();
            containtActivity = true;
            while(containtActivity){
                if (activity.getActivityDate().after(currentDate)) {
                    // NO ACTIVITY TODAY
                    listAllDays.add(mapReport = new HashMap());
                    mapReport.put("date_activity", currentDate);
                    
                    calendar.add(Calendar.DATE, 1);
                    currentDate = new java.sql.Date(calendar.getTime().getTime());
                } else {
                    listOneDay = new ArrayList();
                    while(containtActivity && activity.getActivityDate().toString().equals(currentDate.toString())){
                        listOneDay.add(mapReport = convertActivityToMap( activity));
                        containtActivity = it.hasNext();
                        activity = containtActivity?it.next():null;
                    }
                    listAllDays.addAll(listOneDay);
                }
            }
        }
        calendar.add(Calendar.DATE, 1);
        currentDate = new java.sql.Date(calendar.getTime().getTime());
        while (endDate.after(currentDate) || endDate.toString().equals(currentDate.toString())) {
            // NO ACTIVITY TODAY
            listAllDays.add(mapReport = new HashMap());
            mapReport.put("date_activity", new java.sql.Date(currentDate.getTime()));
            
            calendar.add(Calendar.DATE, 1);
            currentDate = new java.sql.Date(calendar.getTime().getTime());
        }
        return new JRMapCollectionDataSource(listAllDays);
    }
}