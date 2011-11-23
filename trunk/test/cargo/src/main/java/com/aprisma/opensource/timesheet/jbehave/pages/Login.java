package com.aprisma.opensource.timesheet.jbehave.pages;


public interface Login {
    
    void go();

    void loginUsingUserNameAndPassword(String userName, String password);

    public void logout();
}
