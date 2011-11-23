package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Absent;
import java.util.Date;

/**
 *
 * @author devxpbox
 */
public class AbsentOneDayForm extends BasePage {

    
    private Absent absent = new Absent();

    private String[] types=new String[]{"cuti","unpaid cuti","sakit","bolos"};
    
    
    public Absent getAbsent() {
        return absent;
    }

    public void setAbsent(Absent absent) {
        this.absent = absent;
    }

    public Date getCheckDate() 
    {
        return absent.getCheckDate();
    }

    public void setCheckDate(Date checkDate) {

    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String save() {
        
        return "mainMenu";
    }
}
