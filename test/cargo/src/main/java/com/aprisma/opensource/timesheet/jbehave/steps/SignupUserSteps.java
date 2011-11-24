package com.aprisma.opensource.timesheet.jbehave.steps;

import com.aprisma.opensource.timesheet.jbehave.spring.Steps;
import org.jbehave.core.annotations.AfterScenario;
import com.aprisma.opensource.timesheet.jbehave.pages.Signup;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.hamcrest.Matchers;
import org.jbehave.core.annotations.BeforeScenario;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.wiser.Wiser;

@Steps
public class SignupUserSteps {
    
    @Autowired
    private Signup signUp;

    private Wiser wiser = new Wiser();

    @BeforeScenario
    public void beforeSecenario(){ 
        try{
            wiser.start();
        }catch(Exception e){}
    }
    
    @AfterScenario
    public void afterSecenario(){
        try{
            wiser.stop();
        }catch(Exception e){}
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
    
    @Then("show Signup success message \"$message\"")
    public void thenSuccessMessages(String message) {
        assertThat(signUp.getSuccessMessages(), Matchers.containsString(message));
    }
}
