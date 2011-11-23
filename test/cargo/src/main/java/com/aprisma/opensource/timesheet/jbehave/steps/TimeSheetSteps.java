package com.aprisma.opensource.timesheet.jbehave.steps;

import com.aprisma.opensource.timesheet.jbehave.pages.CheckRoll;
import com.aprisma.opensource.timesheet.jbehave.pages.Generic;
import org.jbehave.core.annotations.AfterStories;
import com.aprisma.opensource.timesheet.jbehave.spring.Steps;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.AfterScenario;
import com.aprisma.opensource.timesheet.jbehave.pages.Login;
import org.jbehave.core.annotations.Pending;
import com.aprisma.opensource.timesheet.jbehave.pages.Signup;

import java.util.Date;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
/*
import org.jbehave.tutorials.etsy.pages.AdvancedSearch;
import org.jbehave.tutorials.etsy.pages.Buy;
import org.jbehave.tutorials.etsy.pages.CartContents;
import org.jbehave.tutorials.etsy.pages.Home;
import org.jbehave.tutorials.etsy.pages.PageFactory;
import org.jbehave.tutorials.etsy.pages.SearchResults;
import org.jbehave.tutorials.etsy.pages.Site;
import org.jbehave.tutorials.etsy.pages.Treasury;
 */
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import org.jbehave.web.selenium.PerScenarioWebDriverSteps;
import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.wiser.Wiser;

@Steps
public class TimeSheetSteps {

    @Autowired
    private Generic generic;
    
    @Autowired
    private CheckRoll checkRoll;
    
    @When("click menu Check Roll menu")
    public void whenClickMenuCheckRollMenu() {
        checkRoll.clickMenuCheckRollMenu();
    }

    @Then("show Check Roll form")
    public void thenShowCheckRollForm() {
        assertThat(generic.getCurrentUrl(), Matchers.endsWith("/checkroll"));
    }

    @When("fill in data to Check Roll Date value $checkRollDate , Check In Time value $checkInTime , Check Out Time value $checkOutTime , click Save button")
    public void whenFillInDataToCheckRollDateValueCheckInTimeValueCheckOutTimeValueClickSaveButton(String checkRollDate, String checkInTime, String checkOutTime) {
        checkRoll.fillData(checkRollDate,checkInTime,checkOutTime);
        checkRoll.clickButtonSave();
    }

    @Then("show message data Check Roll has been saved")
    @Pending
    public void thenShowMessageDataCheckRollHasBeenSaved() {
        // PENDING
    }
}
