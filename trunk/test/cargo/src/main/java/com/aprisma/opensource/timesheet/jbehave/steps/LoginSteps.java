package com.aprisma.opensource.timesheet.jbehave.steps;


import com.aprisma.opensource.timesheet.jbehave.spring.Steps;
import com.aprisma.opensource.timesheet.jbehave.pages.Login;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.hamcrest.Matchers;
import org.jbehave.core.annotations.AfterScenario;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

@Steps
public class LoginSteps {
    
    @Autowired
    private Login login;
    
    @AfterScenario
    public void logout(){
        login.goToPath("logout");
    }
    
    @Given("I am on Login Form TimeSheet")
    public void givenIAmOnLoginFormTimeSheet() {
        login.go();
    }

    @When("do login $userName $password")
    public void whenDoLogin(String userName, String password) {
        login.loginUsingUserNameAndPassword(userName, password);
    }

    @Then("authenticated")
    public void thenAuthenticated() {
        assertThat(login.getCurrentUrl(), Matchers.containsString("/mainMenu"));
    }

    @Then("login fail")
    public void thenLoginFail() {
        assertThat(login.getCurrentUrl(), Matchers.containsString("/login?error=true"));
    }

    @When("logout")
    public void whenLogout() {
        login.logout();
    }

    @Then("login form")
    public void thenLoginForm() {
        assertThat(login.getCurrentUrl(), Matchers.containsString("/login"));
    }
}
