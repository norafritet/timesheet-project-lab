/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.jbehave;

import org.junit.Ignore;
import org.jbehave.core.io.StoryFinder;
import java.util.List;
import org.jbehave.core.ConfigurableEmbedder;
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
        Embedder embedder = timeSheetStories.configuredEmbedder();
    @Test 
    //@Ignore
    public void testRun_SignupUser(){

        List<String> list = asList("**/signup_user.story");
        String file = codeLocationFromClass(timeSheetStories.getClass()).getFile();
        embedder.runStoriesAsPaths(new StoryFinder().findPaths(file,list , null));
        
    }
}
