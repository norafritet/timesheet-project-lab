package com.aprisma.opensource.timesheet.jbehave.pages;


public interface Login {
    
    void go();

    void loginUsingUserNameAndPassword(String userName, String password);
    
    String getCurrentUrl();

    public void logout();
}
