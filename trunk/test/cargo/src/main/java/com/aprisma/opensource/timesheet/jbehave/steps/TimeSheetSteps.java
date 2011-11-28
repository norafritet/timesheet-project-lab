package com.aprisma.opensource.timesheet.jbehave.steps;

import com.aprisma.opensource.timesheet.jbehave.pages.InquiryActivity;
import com.aprisma.opensource.timesheet.jbehave.pages.Absent;
import com.aprisma.opensource.timesheet.jbehave.pages.Activity;
import com.aprisma.opensource.timesheet.jbehave.pages.Attendance;
import java.net.MalformedURLException;
import com.aprisma.opensource.timesheet.jbehave.spring.Steps;
import org.jbehave.core.annotations.Pending;

import java.net.URL;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

@Steps
public class TimeSheetSteps {

    @Autowired
    private Attendance attendance;
    @Autowired
    private Activity activity;
    @Autowired
    private Absent absent;
    @Autowired
    private InquiryActivity inquiryActivity;
    
    @When("click menu Check Roll menu")
    public void whenClickMenuCheckRollMenu() {
        attendance.clickMenuCheckRollMenu();
    }

    @Then("show Check Roll form")
    public void thenShowCheckRollForm() throws MalformedURLException {
        //URL url = new URL(attendance.getCurrentUrl());
        //assertThat(url.getPath(), Matchers.containsString("/checkRoll"));
    }

    @When("fill in data to Check Roll Date value $checkRollDate , Check In Time value $checkInTime , Check Out Time value $checkOutTime , click Save button")
    public void whenFillInDataToCheckRollDateValueCheckInTimeValueCheckOutTimeValueClickSaveButton(String checkRollDate, String checkInTime, String checkOutTime) {
        attendance.fillData(checkRollDate, checkInTime, checkOutTime);
        attendance.clickButtonSave();
    }

    @Then("show message data Check Roll has been saved")
    public void thenShowMessageDataCheckRollHasBeenSaved() {
        assertThat(attendance.getSuccessMessages(), Matchers.containsString("Attendance information has been added successfully."));
    }

    // activity step
    @When("click menu Activity menu")
    public void whenClickMenuActivityMenu() {
        activity.clickMenuActivityMenu();
    }

    @Then("show Activity form")
    public void thenShowActivityForm() {
        // PENDING
    }

    @Then("default activity date is today")
    public void thenDefaultActivityDateIsToday() {
        // PENDING
    }

    @When("fill in data to Activity Form Date value $activityDate , Time from value $timeFrom , Time to value $timeTo , Select $selectType , Activities Name $acctifityName, Case $cassNo , ICenter No $icenter , Location $location , Status $status , Remark is $remark .")
    public void whenFillInDataToActivityForm(String activityDate, String timeFrom, String timeTo, String selectType, String acctifityName, String cassNo, String icenter, String location, String status, String remark) {
        activity.fillInDataToActivityForm(activityDate, timeFrom, timeTo, selectType, acctifityName, cassNo, icenter, location, status, remark);
    }

    @When("fill in Mandatory data to Activity Form Date value $activityDate , Time from value $timeFrom , Time to value $timeTo , Activities Name $acctifityName .")
    public void whenFillInDataToActivityForm(String activityDate, String timeFrom, String timeTo, String acctifityName) {
        activity.fillInDataToActivityForm(activityDate, timeFrom, timeTo, acctifityName);
    }

    @When("click Activity Save Button")
    public void whenClickActivitySaveButton() {
        activity.clickActivitySaveButton();
    }

    @Then("show message data Activity has been saved")
    public void thenShowMessageDataActivityHasBeenSaved() {
        assertThat(activity.getSuccessMessages(), Matchers.containsString("success"));
    }

    @When("click menu One Day Absent menu")
    public void whenClickMenuOneDayAbsentMenu() {
        absent.clickMenuOneDayAbsentMenu();
    }

    @Then("show One Day Absent form")
    public void thenShowOneDayAbsentForm() {
        // PENDING
    }

    @When("fill in data to absent Form Date value $absentDate , Select value $type for type, reason value no $reason , click Save button")
    public void whenFillInDataToAbsentFormDateValueSelectValueTypeReasonValueNoReasonClickSaveButton(String absentDate, String type, String reason) {
        absent.fillInData(absentDate, type, reason);
        absent.clickButtonSave();
    }

    @Then("show message data One Day Absent has been saved")
    public void thenShowMessageDataOneDayAbsentHasBeenSaved() {
        assertThat(absent.getSuccessMessages(), Matchers.containsString("Your Absent has been submit successfully."));
    }

    @When("click menu Range Day Absent menu")
    public void whenClickMenuRangeDayAbsentMenu() {
        absent.clickMenuRangeDayAbsentMenu();
    }

    @Then("show Range Day Absent form")
    public void thenShowRangeDayAbsentForm() {
        // PENDING
    }

    @When("fill in data to Range Day Absent Form From Date value $fromDate , To Date value $toDate ,  Select value $type for type, reason value $reason , click Save button")
    public void whenFillInDataToAbsentFormDateValueSelectValueTypeReasonValueNoReasonClickSaveButton(String fromDate, String toDate, String type, String reason) {
        absent.fillInRangeAbsent(fromDate, toDate, type, reason);
        absent.clickRangeButtonSave();
    }
    
    @Then("show message data Range Day Absent has been saved")
    public void thenShowMessageDataRangeDayAbsentHasBeenSaved() {
        assertThat(absent.getSuccessMessages(), Matchers.containsString("Your Range Absent has been submit successfully."));
    }
    @When("click menu Inquiry Activity")
    public void whenClickMenuInquiryActivity() {
        inquiryActivity.clickMenuInquiryActivity();
    }
    
    @Then("show Inquiry Activity")
    public void thenShowInquiryActivity() {
        //PENDING
    }
    
    @When("fill in inquiry form select Year $year , Month $month , Week $week .")
    public void whenFillInInquiryFormSelect(String year, String month, String week) {
        inquiryActivity.fillInquiryForm(year, month, week);
    }
    
    @When("click inquiry button view")
    public void whenClickInquiryButtonView() {
        inquiryActivity.clickInquiryButtonView();
    }
    @When("click inquiry button download")
    public void whenClickInquiryButtonDownload() {
        inquiryActivity.clickInquiryButtonDownload();
    }
    
    @Then("show list activity one week.")
    @Pending
    public void thenShowListActivityOneWeek() {
        // PENDING
    }

    @Then("show list activity one month.")
    @Pending
    public void thenShowListActivityOneMonth() {
        // PENDING
    }

    @Then("show list activity one year.")
    @Pending
    public void thenShowListActivityOneYear() {
        // PENDING
    }
}
