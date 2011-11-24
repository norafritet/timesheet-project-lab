/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;

import mockit.Verifications;
import mockit.NonStrictExpectations;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Verifier;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class AbsentRangeDayFormTest {
    private AbsentRangeDayForm form ;
    
    private void context_save_InputAbsent(){
        form = new AbsentRangeDayForm();
        new NonStrictExpectations(form){
            {
           form.addMessage("absent_range_day.added");
            }
        };
    }
    
    @Test
    public void save_InputAbsent_Call$addMessage(){
        context_save_InputAbsent();
        
        form.save();
        
        new Verifications(){
            {
            form.addMessage("absent_range_day.added");
            }
        };
    }
    @Test
    public void save_InputAbsent_ReturnsMainMenu(){
        context_save_InputAbsent();
        
        String result = form.save();
        
        assertEquals("mainMenu", result );

    }
}
