package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Attendance;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/5/11
 * Time: 3:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class AttendanceForm extends BasePage {

    private Attendance attendance = new Attendance();
    private GenericManager<Attendance, Long> checkManager;

    public String save() {

        String username = getRequest().getRemoteUser();
        User user = userManager.getUserByUsername(username);
        attendance.setCheckUser(user);
        attendance = checkManager.save(attendance);
        addMessage("absent_one_day.added");

        return "mainMenu";
    }
}
