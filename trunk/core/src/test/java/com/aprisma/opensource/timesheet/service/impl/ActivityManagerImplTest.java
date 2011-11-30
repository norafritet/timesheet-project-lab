/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service.impl;

import com.aprisma.opensource.timesheet.dao.ActivityDao;
import net.sf.jasperreports.engine.JRDataSource;
import java.sql.Date;
import mockit.Injectable;
import mockit.Tested;
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
    @Tested private ActivityManagerImpl activityManager;
    @Injectable private ActivityDao activityDao;
    
    public ActivityManagerImplTest() {
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

    @Test
    public void getJRDataSourceActivity_AnyInput_ReturnNotNull() throws Exception {
        JRDataSource result = activityManager.getJRDataSourceActivity((long)1, new Date(1), new Date(1));
        assertNotNull(result);
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
