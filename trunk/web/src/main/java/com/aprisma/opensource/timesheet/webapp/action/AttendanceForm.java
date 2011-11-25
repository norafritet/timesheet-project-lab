package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Attendance;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/5/11
 * Time: 3:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class AttendanceForm extends BasePage implements Serializable {

    private Attendance attendance = new Attendance();
    private GenericManager<Attendance, Long> attendanceManager;
    //private Date checkIn;
    //private Date checkOut;

    public Date getCheckOut() {
        return attendance.getCheckOut();
    }

    public void setCheckOut(Date checkOut) {

        attendance.setCheckOut(new Time(checkOut.getTime()));
    }

    public Date getCheckIn() {
        return attendance.getCheckIn();
    }

    public void setCheckIn(Date checkIn) {
        //this.checkIn = checkIn;
        attendance.setCheckIn(new Time(checkIn.getTime()));
    }

    public Date getCheckDate() {
        return attendance.getCheckDate();
    }

    public void setCheckDate(Date checkDate) {
        //this.checkIn = checkIn;
        attendance.setCheckDate( new java.sql.Date(checkDate.getTime()));
    }

    public GenericManager<Attendance, Long> getAttendanceManager() {
        return attendanceManager;
    }

    public void setAttendanceManager(GenericManager<Attendance, Long> attendanceManager) {
        this.attendanceManager = attendanceManager;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public String save() {

        String username = getRequest().getRemoteUser();
        User user = userManager.getUserByUsername(username);
        attendance.setCheckUser(user);

        attendanceManager.save(attendance);
        addMessage("attendance.added");

        return "mainMenu";
    }
}
