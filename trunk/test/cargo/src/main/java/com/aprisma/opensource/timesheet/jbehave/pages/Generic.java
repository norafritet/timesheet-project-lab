package com.aprisma.opensource.timesheet.jbehave.pages;


public interface Generic {

    String getCurrentUrl() ;

    String getTitle();
    
    String getSuccessMessages();
    
    void goToPath(String path);
    
}
