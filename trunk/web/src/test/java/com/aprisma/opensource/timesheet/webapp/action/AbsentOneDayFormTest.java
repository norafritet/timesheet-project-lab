/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class AbsentOneDayFormTest {
    
    @Test
    public void save_InputAbsent_ReturnsMainMenu(){
        AbsentOneDayForm form = new AbsentOneDayForm();
        
        String result = form.save();
        
        assertEquals("mainMenu", result );

    }
}
