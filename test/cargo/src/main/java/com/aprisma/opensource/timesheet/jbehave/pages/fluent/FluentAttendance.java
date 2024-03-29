package com.aprisma.opensource.timesheet.jbehave.pages.fluent;


import com.aprisma.opensource.timesheet.jbehave.pages.Attendance;
import com.aprisma.opensource.timesheet.jbehave.spring.Page;
import org.openqa.selenium.By;
import com.aprisma.opensource.timesheet.jbehave.pages.Login;
import com.aprisma.opensource.timesheet.jbehave.pages.Signup;
import org.jbehave.web.selenium.WebDriverProvider;

import org.springframework.beans.factory.annotation.Autowired;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.xpath;

@Page 
public class FluentAttendance extends FluentPage implements Attendance {

   private String formName = "attendanceForm:";
            
    @Autowired
    public FluentAttendance(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    
//    @Override
//    public void go() {
//        goToPath("login");
//    }

//    @Override
//    public void go(String section) {
//        go();
//        link(xpath("@title = '" + section + "'")).click();
//    }

//    @Override
//    public void loginUsingUserNameAndPassword(String userName, String password) {
//         input(By.id("j_username")).clearField().sendKeys(userName);
//         input(By.id("j_password")).clearField().sendKeys(password);
//         input(By.name("login")).click();
//    }

//    @Override
//    public void logout() {
//        link(xpath("@title = 'Logout'")).click();
//    }

    @Override
    public void clickMenuCheckRollMenu() {
        //.out.println(link(xpath("@title = 'Check Roll'")).location());
        //goToPath("checkRoll");
        goByTitle("Attendance");
        //link(xpath("@title = 'Check Roll'")).click();
    }

    @Override
    public void fillData(String checkRollDate, String checkInTime, String checkOutTime) {
        input(By.id(formName + "checkDate")).clearField().sendKeys(checkRollDate);
        input(By.id(formName + "checkIn")).clearField().sendKeys(checkInTime);
        input(By.id(formName + "checkOutTime")).clearField().sendKeys(checkOutTime);
    }

    @Override
    public void clickButtonSave() {
        input(By.id(formName + "save")).click();
        
    }

}
