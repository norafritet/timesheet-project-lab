/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.jbehave;

import org.junit.Ignore;
import org.jbehave.core.io.StoryFinder;
import java.util.List;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.embedder.Embedder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 *
 * @author devxpbox
 */
public class StoryTest {
    
    TimeSheetStories timeSheetStories = new TimeSheetStories();
    //Embedder embedder = timeSheetStories.configuredEmbedder();
    
    @Test 
    @Ignore
    public void testRun_SignupUser() throws Throwable{
        System.setProperty("storyFilter", "signup_user");
        timeSheetStories.run();
    }
    @Test 
    @Ignore
    public void testRun_Attendance() throws Throwable{
        System.setProperty("storyFilter", "attendance");
        timeSheetStories.run();
    }
    @Test 
    public void testRun_Absent() throws Throwable{
        System.setProperty("storyFilter", "absent");
        timeSheetStories.run();
    }
    @Test 
    public void testRun_InquiryActivity() throws Throwable{
        System.setProperty("storyFilter", "inquiry_activity");
        timeSheetStories.run();
    }
}
