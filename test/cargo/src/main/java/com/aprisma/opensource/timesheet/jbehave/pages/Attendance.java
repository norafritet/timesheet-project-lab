/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.jbehave.pages;

/**
 *
 * @author devxpbox
 */
public interface Attendance extends Generic{

    public void clickMenuCheckRollMenu();

    public void fillData(String checkRollDate, String checkInTime, String checkOutTime);

    public void clickButtonSave();
    
}
