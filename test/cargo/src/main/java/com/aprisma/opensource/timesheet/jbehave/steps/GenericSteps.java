package com.aprisma.opensource.timesheet.jbehave.steps;

import com.aprisma.opensource.timesheet.jbehave.pages.Generic;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbehave.core.annotations.AfterStories;
import com.aprisma.opensource.timesheet.jbehave.spring.Steps;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.AfterScenario;
import com.aprisma.opensource.timesheet.jbehave.pages.Login;
import org.jbehave.core.annotations.Pending;
import com.aprisma.opensource.timesheet.jbehave.pages.Signup;

import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.hamcrest.Matchers;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.web.selenium.PerScenarioWebDriverSteps;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.wiser.Wiser;

@Steps
public class GenericSteps {

    @Autowired
    private Generic generic;

    @Then("show success message \"$message\"")
    public void thenSuccessMessages(String message) {
        assertThat(generic.getSuccessMessages(), Matchers.containsString(message));
    }
}
