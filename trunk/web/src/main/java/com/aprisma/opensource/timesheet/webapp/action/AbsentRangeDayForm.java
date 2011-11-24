package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Absent;
import java.util.Date;

/**
 *
 * @author devxpbox
 */
public class AbsentRangeDayForm extends BasePage {

    
    private Absent absent = new Absent();

    private Date fromDate;
    private Date toDate  ;
    private String type  ;
    private String remark;
    
    private String[] types=new String[]{"cuti","unpaid cuti","sakit","bolos"};

    public Absent getAbsent() {
        return absent;
    }

    public void setAbsent(Absent absent) {
        this.absent = absent;
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
        
        addMessage("absent_range_day.added"); 
        
        return "mainMenu";
    }
}
