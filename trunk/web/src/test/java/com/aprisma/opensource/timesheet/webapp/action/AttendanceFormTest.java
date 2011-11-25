package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Absent;
import com.aprisma.opensource.timesheet.model.Attendance;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;
import org.appfuse.service.UserManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/5/11
 * Time: 3:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class AttendanceFormTest {

    @Mocked
    private GenericManager<Attendance, Long> attendanceManager;
    @Mocked
    private UserManager userManager;

    //private Date checkIn;
    //private Date checkOut;
    private String username;
    private AttendanceForm form;
    private Attendance attendance;
    private Attendance attendanceResult;
    private User user;
    @Mocked
    private HttpServletRequest httpRequest;


    @Before
    public void onSetUp() {
        username = "user1";
        form = new AttendanceForm();
        attendance = form.getAttendance();
        form.setUserManager(userManager);
        form.setAttendanceManager(attendanceManager);
        user = new User(username);
    }

    private void context_save_InputAttendance() {


        new NonStrictExpectations(form) {

            {
                form.getRequest(); result = httpRequest;

                httpRequest.getRemoteUser();   result = username;
                userManager.getUserByUsername(username); result = user;
                attendanceManager.save(attendance);
                result = new Delegate() {

                    Attendance save(Attendance object) {
                        attendanceResult = new Attendance();

                        //attendanceResult.setId(new Long(0));
                        attendanceResult.setCheckDate(attendance.getCheckDate());

                        attendanceResult.setCheckUser(attendance.getCheckUser());

                        attendanceResult.setCheckIn(attendance.getCheckIn());
                        attendanceResult.setCheckOut(attendance.getCheckOut());
                        return attendanceResult;
                    }
                };
                form.addMessage("attendance.added");
            }
        };
    }

    @Test
    public void save_InputAttendance_Call$getRemoteUser() {
        context_save_InputAttendance();

        form.save();

        new Verifications() {

            {
                httpRequest.getRemoteUser();
            }
        };
    }


    @Test
    public void save_InputAttendance_Call$addMessage() {
        context_save_InputAttendance();

        form.save();

        new Verifications() {

            {
                form.addMessage("attendance.added");
            }
        };
    }



    @Test
    public void save_InputAttendance_UserFromUserManager() {
        context_save_InputAttendance();

        form.save();

        assertEquals(user, form.getAttendance().getCheckUser());
    }




    @Test
    public void save_InputAttendance_Call$getUserByUsername() {
        context_save_InputAttendance();

        form.save();

        assertNotNull(form.getAttendance().getCheckUser());

        new Verifications() {

            {
                userManager.getUserByUsername(username);
            }
        };
    }


    @Test
    public void save_InputAttendance_UserNotNull() {

        context_save_InputAttendance();

        form.save();

        assertNotNull(form.getAttendance().getCheckUser());
    }


    @Test
    public void save_InputAttendance_Call$attendanceManager$save() {
        context_save_InputAttendance();

        form.save();

        new Verifications() {

            {
                attendanceManager.save(attendance);
            }
        };
    }

    @Test
    public void save_InputAttendance_ReturnsMainMenu() {
        context_save_InputAttendance();

        String result = form.save();

        assertEquals("mainMenu", result);
    }

}
