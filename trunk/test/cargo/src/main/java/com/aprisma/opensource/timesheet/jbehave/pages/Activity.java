package com.aprisma.opensource.timesheet.jbehave.pages;


public interface Activity extends Generic{

    public void clickMenuActivityMenu();

    public void fillInDataToActivityForm(String activityDate, String timeFrom, String timeTo, String selectType, String acctifityName, String cassNo, String icenter, String location, String status ,  String remark);

    public void clickActivitySaveButton();

    public void fillInDataToActivityForm(String activityDate, String timeFrom, String timeTo, String acctifityName);
    
}
