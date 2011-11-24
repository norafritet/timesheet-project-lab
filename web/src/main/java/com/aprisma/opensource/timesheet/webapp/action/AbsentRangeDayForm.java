package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Absent;
import java.util.Calendar;
import java.util.Date;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;

/**
 *
 * @author devxpbox
 */
public class AbsentRangeDayForm extends BasePage {

    private Date fromDate;
    private Date toDate;
    private String type;
    private String remark;
    private String[] types = new String[]{"cuti", "unpaid cuti", "sakit", "bolos"};
    private GenericManager<Absent, Long> absentManager;

    public void setAbsentManager(GenericManager<Absent, Long> absentManager) {
        this.absentManager = absentManager;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String save() {

        String username = getRequest().getRemoteUser();

        User user = userManager.getUserByUsername(username);
        Absent absent;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        while (calendar.getTimeInMillis() <= toDate.getTime()) {
            absent = new Absent();
            absent.setCheckUser(user);
            absent.setCheckDate(new java.sql.Date(calendar.getTimeInMillis()));
            absent.setRemark(remark);
            absent.setType(type);
            absentManager.save(absent);
            calendar.add(Calendar.DATE, 1);
        }
        addMessage("absent_range_day.added");

        return "mainMenu";
    }
}
