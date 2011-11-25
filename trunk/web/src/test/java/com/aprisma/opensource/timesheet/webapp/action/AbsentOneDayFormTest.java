/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.action;

import mockit.Delegate;
import org.appfuse.service.GenericManager;
import com.aprisma.opensource.timesheet.model.Absent;
import javax.servlet.http.HttpServletRequest;
import org.appfuse.service.UserManager;
import mockit.Mocked;
import org.junit.Before;
import org.appfuse.model.User;
import java.util.Date;
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
public class AbsentOneDayFormTest {

    private AbsentOneDayForm form;
    @Mocked
    private UserManager userManager;
    @Mocked
    private GenericManager<Absent, Long> absentManager;
    @Mocked
    private HttpServletRequest httpRequest;
    private String username;
    private User user;
    private Absent absent;
    private Absent absentResult;

    @Before
    public void onSetUp() {
        username = "user1";
        form = new AbsentOneDayForm();
        absent = form.getAbsent();
        form.setUserManager(userManager);
        form.setAbsentManager(absentManager);
        user = new User(username);
    }

    private void context_save_InputAbsent() {


        new NonStrictExpectations(form) {

            {
                form.getRequest(); result = httpRequest;
                httpRequest.getRemoteUser();   result = username;
                userManager.getUserByUsername(username); result = user;
                absentManager.save(absent);
                result = new Delegate() {

                    Absent save(Absent object) {
                        absentResult = new Absent();
                        absentResult.setRemark(absent.getRemark());
                        absentResult.setType(absent.getType());
                        absentResult.setCheckDate(absent.getCheckDate());
                        absentResult.setCheckUser(absent.getCheckUser());
                        absentResult.setId((long)-3);
                        return absentResult;
                    }
                };
                form.addMessage("absent_one_day.added");
            }
        };
    }

    @Test
    public void save_InputAbsent_Call$addMessage() {
        context_save_InputAbsent();

        form.save();

        new Verifications() {

            {
                form.addMessage("absent_one_day.added");
            }
        };
    }

    @Test
    public void save_InputAbsent_Call$getUserByUsername() {
        context_save_InputAbsent();

        form.save();

        assertNotNull(form.getAbsent().getCheckUser());

        new Verifications() {

            {
                userManager.getUserByUsername(username);
            }
        };
    }

    @Test
    public void save_InputAbsent_UserFromUserManager() {
        context_save_InputAbsent();

        form.save();

        assertEquals(user, form.getAbsent().getCheckUser());
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
    @Test
    public void save_InputAbsent_Call$absentManager$save() {
        context_save_InputAbsent();

        form.save();

        new Verifications() {

            {
                absentManager.save(absent);
            }
        };
    }
    
    
    @Test
    public void save_InputAbsent_UserNotNull() {

        context_save_InputAbsent();

        form.save();

        assertNotNull(form.getAbsent().getCheckUser());
    }

    @Test
    public void save_InputAbsent_ReturnsMainMenu() {
        context_save_InputAbsent();

        String result = form.save();

        assertEquals("mainMenu", result);
    }

    @Test
    public void setCheckDate_Date_PropertyChange() {
        form = new AbsentOneDayForm();

        Date date = new Date(1);

        form.setCheckDate(date);

        assertEquals(date, form.getCheckDate());
    }

    @Test
    public void setCheckDate_Null_PropertyNull() {
        form = new AbsentOneDayForm();

        Date date = null;

        form.setCheckDate(date);

        assertNull(form.getCheckDate());
    }

    @Test
    public void setCheckDate_Null_PropertyChange() {
        form = new AbsentOneDayForm();
        form.getAbsent().setCheckDate(new java.sql.Date(1));

        Date date = null;

        form.setCheckDate(date);

        assertNull(form.getCheckDate());
    }
}
