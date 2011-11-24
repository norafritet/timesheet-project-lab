package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Absent;
import java.util.Date;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;

/**
 *
 * @author devxpbox
 */
public class AbsentOneDayForm extends BasePage {

    
    private Absent absent = new Absent();

    private String[] types=new String[]{"cuti","unpaid cuti","sakit","bolos"};
    
    private GenericManager<Absent, Long> absentManager;
    
    public void setAbsentManager(GenericManager<Absent, Long> absentManager) {
        this.absentManager =absentManager; 
    }
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
        if(checkDate!=null)
            absent.setCheckDate(new java.sql.Date(checkDate.getTime()));
        else 
            absent.setCheckDate(null);
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
        absent.setCheckUser(user);
        absent = absentManager.save(absent);
        addMessage("absent_one_day.added");
        
        return "mainMenu";
    }


}
