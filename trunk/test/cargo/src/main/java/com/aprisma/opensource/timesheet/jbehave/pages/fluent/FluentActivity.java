package com.aprisma.opensource.timesheet.jbehave.pages.fluent;


import com.aprisma.opensource.timesheet.jbehave.pages.Activity;
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
public class FluentActivity extends FluentPage implements Activity {
private String formName = "activityForm:";
    @Autowired
    public FluentActivity(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }


//    @Override
//    public void go(String section) {
//        go();
//        link(xpath("@title = '" + section + "'")).click();
//    }
//
//    @Override
//    public void loginUsingUserNameAndPassword(String userName, String password) {
//         input(By.id("j_username")).clearField().sendKeys(userName);
//         input(By.id("j_password")).clearField().sendKeys(password);
//         input(By.name("login")).click();
//    }
//
//    @Override
//    public void logout() {
//        link(xpath("@title = 'Logout'")).click();
//    }

    @Override
    public void clickMenuActivityMenu() {
        goToPath("activityForm");
    }

    @Override
    public void fillInDataToActivityForm(String activityDate, String timeFrom, String timeTo, String selectType, String acctifityName, String cassNo, String icenter, String location, String status, String remark) {
        input(By.id(formName+"activityDate")).clearField().sendKeys(activityDate); 
        input(By.id(formName+"timeFrom")).clearField().sendKeys(timeFrom); 
        input(By.id(formName+"timeTo")).clearField().sendKeys( timeTo); 
        select(By.id(formName+"type")).selectByVisibleText(selectType.trim()); 
        input(By.id(formName+"name")).clearField().sendKeys(acctifityName); 
        input(By.id(formName+"activityCase")).clearField().sendKeys(cassNo); 
        input(By.id(formName+"icenterNo")).clearField().sendKeys(icenter); 
        input(By.id(formName+"location")).clearField().sendKeys(location); 
        input(By.id(formName+"activityStatus")).clearField().sendKeys(status);
        input(By.id(formName+"remark")).clearField().sendKeys(remark);
    }

    @Override
    public void clickActivitySaveButton() {
        input(By.id(formName + "save")).click();
    }

    @Override
    public void fillInDataToActivityForm(String activityDate, String timeFrom, String timeTo, String acctifityName) {
        input(By.id(formName+"activityDate")).clearField().sendKeys(activityDate); 
        input(By.id(formName+"timeFrom")).clearField().sendKeys(timeFrom); 
        input(By.id(formName+"timeTo")).clearField().sendKeys( timeTo); 
        //select(By.id(formName+"type")).selectByVisibleText(selectType.trim()); 
        input(By.id(formName+"name")).clearField().sendKeys(acctifityName); 
    }
}
