/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;

import java.util.Date;
import mockit.Delegate;
import org.appfuse.service.GenericManager;
import com.aprisma.opensource.timesheet.model.Absent;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.appfuse.model.User;
import mockit.Mocked;
import org.appfuse.service.UserManager;
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
    @Mocked 
    private UserManager userManager;
    @Mocked 
    private HttpServletRequest httpRequest;
    @Mocked 
    private GenericManager<Absent, Long> absentManager;
    
    private String username;
    private User user;
    
    private long id=-1;
    @Before
    public void onSetUp(){
        username="user1";
        form = new AbsentRangeDayForm();
        form.setUserManager(userManager);
        form.setAbsentManager(absentManager);
        user = new User(username);
        form.setFromDate(new Date(111,0,8));
        form.setToDate(new Date(111,0,8));
    }
    private void context_save_InputAbsent(){

        new NonStrictExpectations(form){
            {
                form.getRequest(); result = httpRequest;
                httpRequest.getRemoteUser();   result = username;
                userManager.getUserByUsername(username); result = user;
                absentManager.save(withAny(new Absent()));
                result = new Delegate() {
                    Absent save(Absent absent) {
                        Absent absentResult = new Absent();
                        absentResult.setRemark(absent.getRemark());
                        absentResult.setType(absent.getType());
                        absentResult.setCheckDate(absent.getCheckDate());
                        absentResult.setCheckUser(absent.getCheckUser());
                        absentResult.setId(id--);
                        return absentResult;
                    }
                };
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
    public void save_FourDayLeave_Call$saveFourTime(){
        context_save_InputAbsent();
        form.setFromDate(new Date(111,0,8));
        form.setToDate(new Date(111,0,11));
        form.save();
        
        new Verifications(){
            {
            absentManager.save(withInstanceOf(Absent.class)); times=4;
            }
        };
    }
    
    @Test
    public void save_InputAbsent_ReturnsMainMenu(){
        context_save_InputAbsent();
        
        String result = form.save();
        
        assertEquals("mainMenu", result );

    }
    
    
    @Test
    public void save_InputAbsent_Call$getRemoteUser() {
        context_save_InputAbsent();

        form.save();

        new Verifications() {

            {
                httpRequest.getRemoteUser();
            }
        };
    }
        
        
}
