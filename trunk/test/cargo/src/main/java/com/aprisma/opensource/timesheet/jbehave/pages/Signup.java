package com.aprisma.opensource.timesheet.jbehave.pages;


public interface Signup extends Generic{
    
    void go(); 
    
    void go(String section);

    public void fillForm(String username, String password, String confirmPassword, String passwordHint, String firstName, String lastName, String email, String phoneNumber, String website, String address, String city, String state, String postalCode,String country);

    public void clickSignup();
    
}
