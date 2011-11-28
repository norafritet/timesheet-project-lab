package com.aprisma.opensource.timesheet.jbehave.pages.fluent;


import com.aprisma.opensource.timesheet.jbehave.pages.InquiryActivity;
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
public class FluentInquiryActivity extends FluentPage implements InquiryActivity {
    private String formName = "inquieryActivityForm:";
    @Autowired
    public FluentInquiryActivity(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    @Override
    public void clickMenuInquiryActivity() {
        //goToPath("checkRoll");
        goByTitle("Inquiry Activity");
    }

    @Override
    public void fillInquiryForm(String year, String month, String week) {
        select(By.id(formName+"year")).selectByVisibleText(year.trim()); 
        select(By.id(formName+"month")).selectByVisibleText(month.trim()); 
        select(By.id(formName+"week")).selectByVisibleText(week.trim()); 
    }

    @Override
    public void clickInquiryButtonView() {
        input(By.id(formName + "view")).click();
    }

    @Override
    public void clickInquiryButtonDownload() {
        input(By.id(formName + "download")).click();
    }

}
