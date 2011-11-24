package com.aprisma.opensource.timesheet.jbehave.pages.fluent;


import com.aprisma.opensource.timesheet.jbehave.pages.Absent;
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
public class FluentAbsent extends FluentPage implements Absent {

    @Autowired
    public FluentAbsent(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }
    private String formName = "absentOneDayForm:";
    private String formRangeName = "absentRangeDayForm:";
    @Override
    public void clickMenuOneDayAbsentMenu() {
        //System.out.println(link(xpath("@title = 'One Day Absent'")).location());
        //goByTitle("One Day Absent");
        goToPath("absentOneDay");
    }

    @Override
    public void fillInData(String checkDate, String absentType, String reason) {
        input(By.id(formName + "checkDate")).clearField().sendKeys(checkDate);
        select(By.id(formName + "absentType")).selectByVisibleText(absentType.trim());
        input(By.id(formName + "reason")).clearField().sendKeys(reason);
    }

    @Override
    public void clickButtonSave() {
        input(By.id(formName + "save")).click();
    }
    
    @Override
    public void clickRangeButtonSave() {
        input(By.id(formRangeName + "save")).click();
    }

    @Override
    public void fillInRangeAbsent(String fromDate, String toDate, String type, String reason) {
        input(By.id(formRangeName + "fromDate")).clearField().sendKeys(toDate);
        input(By.id(formRangeName + "toDate")).clearField().sendKeys(toDate);
        select(By.id(formRangeName + "absentType")).selectByVisibleText(type.trim());
        input(By.id(formRangeName + "reason")).clearField().sendKeys(reason);
    }

    @Override
    public void clickMenuRangeDayAbsentMenu() {
        //goByTitle("One Range Absent");
        goToPath("absentRangeDay");
    }
}
