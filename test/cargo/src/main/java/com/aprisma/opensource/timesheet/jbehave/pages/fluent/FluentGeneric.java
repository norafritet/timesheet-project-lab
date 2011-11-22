package com.aprisma.opensource.timesheet.jbehave.pages.fluent;

import com.aprisma.opensource.timesheet.jbehave.pages.Generic;
import com.aprisma.opensource.timesheet.jbehave.spring.Page;
import com.aprisma.opensource.timesheet.jbehave.pages.Signup;
import org.jbehave.web.selenium.WebDriverProvider;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.xpath;

@Page 
public class FluentGeneric extends FluentPage implements Generic {

    @Autowired
    public FluentGeneric(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    @Override
    public String getSuccessMessages() {
        return div(By.id("successMessages")).getText().trim();
    }
}
