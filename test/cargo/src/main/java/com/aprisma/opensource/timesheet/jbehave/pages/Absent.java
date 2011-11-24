package com.aprisma.opensource.timesheet.jbehave.pages;


public interface Absent extends Generic{

    void clickMenuOneDayAbsentMenu();

    void fillInData(String absentDate, String type, String reason);

    void clickButtonSave();

    public void fillInRangeAbsent(String fromDate, String toDate, String type, String reason);

    public void clickRangeButtonSave();

    public void clickMenuRangeDayAbsentMenu();
    
}
