/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.sql.Time;
import org.appfuse.model.User;
import com.aprisma.opensource.timesheet.model.Activity;
import java.util.List;
import java.util.ArrayList;
import mockit.Mocked;
import com.aprisma.opensource.timesheet.dao.ActivityDao;
import net.sf.jasperreports.engine.JRDataSource;
import java.sql.Date;
import java.util.Arrays;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class ActivityManagerImplTest {
    private ActivityManagerImpl activityManagerImpl;
    
    @Mocked private ActivityDao activityDao;
    
 
    
    @Before
    public void setUp() {
        activityManagerImpl = new ActivityManagerImpl();
        activityManagerImpl.setActivityDao(activityDao);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getJRDataSourceActivity_AnyInput_ReturnNotNull() throws Exception {
        JRDataSource result = activityManagerImpl.getJRDataSourceActivity((long)1, new Date(1), new Date(1));
        assertNotNull(result);
    }
    @Test
    public void getJRDataSourceActivity_SecondWeek_ReturnEightRow() throws Exception {
        
        final List<Activity> list = new ArrayList();
        Activity activity;
        User user = new User();
        Date date = Date.valueOf("2011-01-03");
        Time from = Time.valueOf("08:30:00");
        Time to   = Time.valueOf("11:30:00");
        
        list.add(activity = new Activity());
        activity.setActivityUser(user);
        activity.setActivityDate(date);
        activity.setTimeFrom(from);
        activity.setTimeTo(to);
        activity.setType("ANL");
        activity.setName("Masuk lagi ");
        activity.setActivityCase("case1");
        activity.setIcenterNo("JIRA-12");
        activity.setActivityStatus("Siaga1");
        activity.setLocation("Aprisma");
        activity.setRemark("Remark 23");
        
        date = Date.valueOf("2011-01-03");
        from = Time.valueOf("13:30:00");
        to   = Time.valueOf("17:30:00");
        list.add(activity = new Activity());
        activity.setActivityUser(user);
        activity.setActivityDate(date);
        activity.setTimeFrom(from);
        activity.setTimeTo(to);
        activity.setType("ANL");
        activity.setName("Masuk lagi");
        activity.setActivityCase("case1");
        activity.setIcenterNo("JIRA-12");
        activity.setActivityStatus("Siaga1");
        activity.setLocation("Aprisma");
        activity.setRemark("Remark 23");
        
        final Date fromDate = Date.valueOf("2011-01-02");
        final Date toDate = Date.valueOf("2011-01-08");
        
        new NonStrictExpectations(){{activityDao.findByActivityWeek((long)1, fromDate, toDate);result=list;}};
        
        JRMapCollectionDataSource result = (JRMapCollectionDataSource) activityManagerImpl.getJRDataSourceActivity((long)1, fromDate, toDate);
        
        assertEquals(8,result.getRecordCount());
    }   
    
    @Test
    public void getJRDataSourceActivity_SecondWeek_ReturnSevenRow() throws Exception {
        
        final List<Activity> list = new ArrayList();
        Activity activity;
        User user = new User();
        Date date = Date.valueOf("2011-01-03");
        Time from = Time.valueOf("08:30:00");
        Time to   = Time.valueOf("17:30:00");
        list.add(activity = new Activity());
        activity.setActivityUser(user);
        activity.setActivityDate(date);
        activity.setTimeFrom(from);
        activity.setTimeTo(to);
        activity.setType("ANL");
        activity.setName("Masuk lagi ");
        activity.setActivityCase("case1");
        activity.setIcenterNo("JIRA-12");
        activity.setActivityStatus("Siaga1");
        activity.setLocation("Aprisma");
        activity.setRemark("Remark 23");
        
        
        final Date fromDate = Date.valueOf("2011-01-02");
        final Date toDate = Date.valueOf("2011-01-08");
        
        new NonStrictExpectations(){{activityDao.findByActivityWeek((long)1, fromDate, toDate);result=list;}};
        
        JRMapCollectionDataSource result = (JRMapCollectionDataSource) activityManagerImpl.getJRDataSourceActivity((long)1, fromDate, toDate);
        
        assertEquals(7,result.getRecordCount());
    }   
    
//    @Test
//    public void getJRDataSourceActivity_SecondWeek_ReturnFullJRDataSource() throws Exception {
//        
//        final List<Activity> list = new ArrayList();
//        Activity activity;
//        User user = new User();
//        Date date = Date.valueOf("2011-01-03");
//        Time from = Time.valueOf("08:30:00");
//        Time to   = Time.valueOf("17:30:00");
//        list.add(activity = new Activity());
//        activity.setActivityUser(user);
//        activity.setActivityDate(date);
//        activity.setTimeFrom(from);
//        activity.setTimeTo(to);
//        activity.setType("ANL");
//        activity.setName("Masuk lagi");
//        activity.setActivityCase("case1");
//        activity.setIcenterNo("JIRA-12");
//        activity.setActivityStatus("Siaga1");
//        activity.setLocation("Aprisma");
//        activity.setRemark("Remark 23");
//        final Date fromDate = Date.valueOf("2011-01-02");
//        final Date toDate = Date.valueOf("2011-01-08");
//        
//        Map mapReport; 
//        List<Map> listResult = new ArrayList<Map>();
//        
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", Date.valueOf("2011-01-02"));
//        
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", activity.getActivityDate());
//        mapReport.put("time_from", activity.getActivityDate());    
//        mapReport.put("time_to", activity.getActivityDate());  
//        mapReport.put("code", activity.getType());
//        mapReport.put("activities", activity.getName());    
//        mapReport.put("case", activity.getActivityCase());  
//        mapReport.put("i_center_no", activity.getIcenterNo());
//        mapReport.put("status", activity.getActivityStatus());    
//        mapReport.put("location", activity.getLocation());  
//        mapReport.put("remark", activity.getRemark());
//
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", Date.valueOf("2011-01-04"));
//
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", Date.valueOf("2011-01-05"));
//        
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", Date.valueOf("2011-01-06"));
//        
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", Date.valueOf("2011-01-07"));
//        
//        listResult.add(mapReport= new HashMap());
//        mapReport.put("date_activity", Date.valueOf("2011-01-08"));
//        
//        new NonStrictExpectations(){{activityDao.findByActivityWeek((long)1, fromDate, toDate);result=list;}};
//        
//        JRMapCollectionDataSource result = (JRMapCollectionDataSource) activityManagerImpl.getJRDataSourceActivity((long)1, fromDate, toDate);
//        
//        assertEquals(listResult,result.getData());
//    }    
    // TODO add test methods here.
    //
    // Pola dari : The Art of Unit Tesing pathen
    //             methodOrFunction_Secenario_Behavior
    @Ignore
    @Test
    public void methodOrFunction_Secenario_Behavior() throws Exception {
        ;
    }
}
