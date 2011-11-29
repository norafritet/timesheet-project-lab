/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.search;

import org.appfuse.webapp.PostProcessor;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.compass.core.config.CompassConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class CompassConfigurationPostProcessorTest {

    @Test
    public void process_rootPacakageNotOrgAppfuse_Call$addScan(@Mocked final CompassConfiguration config){
        CompassConfigurationPostProcessor processor = new CompassConfigurationPostProcessor();
        new NonStrictExpectations(){{config.addScan(anyString);}};
        
        processor.process(config);
        new Verifications(){{config.addScan(anyString);times=1;}};
        
    }
    
    @Test
    public void process_rootPacakageOrgAppfuse_NotCall$addScan(@Mocked final CompassConfiguration config){
        CompassConfigurationPostProcessor processor = new PostProcessor();
        new NonStrictExpectations(){{config.addScan(anyString);}};
        
        processor.process(config);
        new Verifications(){{config.addScan(anyString);times=0;}};
        
    }
}
