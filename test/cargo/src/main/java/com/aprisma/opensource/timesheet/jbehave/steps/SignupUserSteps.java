package com.aprisma.opensource.timesheet.jbehave.steps;

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
public class SignupUserSteps {

    @Autowired
    private Signup signUp;

    private Wiser wiser = new Wiser();

    @BeforeScenario
    public void beforeSecenario(){
        wiser.start();
    }
    
    @AfterScenario
    public void afterSecenario(){
        wiser.stop();
    }
    
    @Given("Form SignUp")
    public void givenFormSignUp() {
        signUp.go();
    }

    @When("fill $username , $password , $confirmPassword , $passwordHint , $firstName , $lastName , $email , $phoneNumber , $website , $address , $city , $province , $postalCode , $country")
    public void whenFill(String username, String password, String confirmPassword, String passwordHint, String firstName, String lastName, String email, String phoneNumber, String website, String address, String city, String province, String postalCode, String country) {
        signUp.fillForm( username,  password,  confirmPassword,  passwordHint,  firstName,  lastName,  email,  phoneNumber,  website,  address,  city,  province,  postalCode, country);
    }

    @When("click signup button")
    public void whenClickSignupButton() {
        signUp.clickSignup();
         
    }
 
}
