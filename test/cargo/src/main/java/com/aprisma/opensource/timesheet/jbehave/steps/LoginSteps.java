package com.aprisma.opensource.timesheet.jbehave.steps;


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
public class LoginSteps {

    @Autowired
    private Login login;
    

    @Given("I am on Login Form TimeSheet")
    public void givenIAmOnLoginFormTimeSheet() {
        System.out.print(this.getClass());
        login.go();
    }

    @When("do login $userName $password")
    public void whenDoLogin(String userName, String password) {
        login.loginUsingUserNameAndPassword(userName, password);
    }

    @Then("authenticated")
    public void thenAuthenticated() {
        assertThat(login.getCurrentUrl(), Matchers.endsWith("/TimeSheet/mainMenu"));
    }

    @Then("login fail")
    public void thenLoginFail() {
        assertThat(login.getCurrentUrl(), Matchers.endsWith("/TimeSheet/login?error=true"));
    }

    @When("logout")
    public void whenLogout() {
        login.logout();
    }

    @Then("login form")
    public void thenLoginForm() {
        assertThat(login.getCurrentUrl(), Matchers.endsWith("/TimeSheet/login"));
    }
}
