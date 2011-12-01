/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service.impl;

import mockit.Delegate;
import org.jmock.Expectations;
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
import mockit.Deencapsulation;
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

    
    private Activity act;
    
    @Mocked 
    private ActivityDao activityDao;
    private List<Activity> listActivity = new ArrayList();
    
    private Date fromDate = Date.valueOf("2011-10-10");
    private Date toDate = Date.valueOf("2011-10-15");
    
    @Before
    public void setUp() {
        activityManagerImpl = new ActivityManagerImpl();
        activityManagerImpl.setActivityDao(activityDao);
    }
    
    private void context_findByActivityWeek_setActivityWeek() {
                final User user = new User();
          
                Date date = Date.valueOf("2011-10-12");
                Time from = Time.valueOf("08:30:00");
                Time to   = Time.valueOf("18:30:00");
                listActivity.add(act = new Activity());
                act.setActivityUser(user);
                act.setActivityDate(date);
                act.setTimeFrom(from);
                act.setTimeTo(to);
                act.setType(" ");
                act.setName(" ");
                act.setActivityCase(" ");
                act.setIcenterNo(" ");
                act.setActivityStatus(" ");
                act.setLocation(" ");
                act.setRemark(" ");

                date = Date.valueOf("2011-10-14");
                from = Time.valueOf("08:30:00");
                to   = Time.valueOf("18:30:00");
                listActivity.add(act = new Activity());
                act.setActivityUser(user);
                act.setActivityDate(date);
                act.setTimeFrom(from);
                act.setTimeTo(to);
                act.setType(" ");
                act.setName(" ");
                act.setActivityCase(" ");
                act.setIcenterNo(" ");
                act.setActivityStatus(" ");
                act.setLocation(" ");
                act.setRemark(" ");

                date = Date.valueOf("2011-10-11");
                from = Time.valueOf("08:30:00");
                to   = Time.valueOf("18:30:00");
                listActivity.add(act = new Activity());
                act.setActivityUser(user);
                act.setActivityDate(date);
                act.setTimeFrom(from);
                act.setTimeTo(to);
                act.setType(" ");
                act.setName(" ");
                act.setActivityCase(" ");
                act.setIcenterNo(" ");
                act.setActivityStatus(" ");
                act.setLocation(" ");
                act.setRemark(" ");
        new NonStrictExpectations() {
            {
                activityDao.findByActivityWeek((long)1, fromDate, toDate);
                result = listActivity;
           }
        };
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void findByActivityWeek_anyInput_ReturnIsSame(){
        context_findByActivityWeek_setActivityWeek();
        
        List<Activity> a = activityManagerImpl.findByActivityWeek((long)1, fromDate, toDate);
        System.out.println("a = "+a);
        System.out.println("result = "+listActivity);
        assertSame(listActivity , a);
        
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
    
    @Test 
    public void convertActivityToMap_InputActivity_ReturnMap(){
        Date date = Date.valueOf("2011-01-03");
        Time from = Time.valueOf("08:30:00");
        Time to   = Time.valueOf("17:30:00");
        
        Map<String, Object> mapReport = new HashMap();
        mapReport.put("date_activity", date);
        mapReport.put("time_from", from );
        mapReport.put("time_to", to);
        mapReport.put("code", "code");
        mapReport.put("activities", "activities");
        mapReport.put("case", "case");
        mapReport.put("i_center_no", "i_center_no");
        mapReport.put("status", "status");
        mapReport.put("location", "location");
        mapReport.put("remark", "remark");
        
        Activity activity = new Activity();
        activity.setActivityDate(date);
        activity.setTimeFrom(from);
        activity.setTimeTo(to);
        activity.setType("code");
        activity.setName("activities");
        activity.setActivityCase("case");
        activity.setIcenterNo("i_center_no");
        activity.setActivityStatus("status");
        activity.setLocation("location");
        activity.setRemark("remark");
        
        Map result = ActivityManagerImpl.convertActivityToMap(activity);
        assertEquals(mapReport,result);
    
    }
    @Test
    public void getJRDataSourceActivity_SecondWeek_ReturnFullJRDataSource() throws Exception {
        
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
        activity.setName("Masuk lagi");
        activity.setActivityCase("case1");
        activity.setIcenterNo("JIRA-12");
        activity.setActivityStatus("Siaga1");
        activity.setLocation("Aprisma");
        activity.setRemark("Remark 23");
        final Date fromDate = Date.valueOf("2011-01-02");
        final Date toDate = Date.valueOf("2011-01-08");
        
        Map mapReport; 
        List<Map> listResult = new ArrayList<Map>();
        
        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", Date.valueOf("2011-01-02"));
        
        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", date);
        mapReport.put("time_from", from);    
        mapReport.put("time_to", to);  
        mapReport.put("code", "ANL");
        mapReport.put("activities", "Masuk lagi");    
        mapReport.put("case", "case1");  
        mapReport.put("i_center_no", "JIRA-12");
        mapReport.put("status", "Siaga1");    
        mapReport.put("location", "Aprisma");  
        mapReport.put("remark", "Remark 23");

        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", Date.valueOf("2011-01-04"));

        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", Date.valueOf("2011-01-05"));
        
        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", Date.valueOf("2011-01-06"));
        
        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", Date.valueOf("2011-01-07"));
        
        listResult.add(mapReport= new HashMap());
        mapReport.put("date_activity", Date.valueOf("2011-01-08"));
        
        new NonStrictExpectations(){{activityDao.findByActivityWeek((long)1, fromDate, toDate);result=list;}};
        
        JRMapCollectionDataSource result = (JRMapCollectionDataSource) activityManagerImpl.getJRDataSourceActivity((long)1, fromDate, toDate);
        
        assertEquals(listResult,result.getData());
    }    
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
