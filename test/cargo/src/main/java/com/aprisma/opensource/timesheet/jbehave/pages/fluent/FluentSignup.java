package com.aprisma.opensource.timesheet.jbehave.pages.fluent;

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
public class FluentSignup extends FluentPage implements Signup {

    @Autowired
    public FluentSignup(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    @Override
    public void go() {
        goToPath("signup");
    }

    @Override
    public void go(String section) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fillForm(String username, String password, String confirmPassword, String passwordHint, String firstName, String lastName, String email, String phoneNumber, String website, String address, String city, String province,String postalCode, String country) {
        String prefix = "signupForm:";
        input(By.id(prefix + "username")).clearField().sendKeys(username);
        input(By.id(prefix + "password")).clearField().sendKeys(password);
        input(By.id(prefix + "confirmPassword")).clearField().sendKeys(confirmPassword);
        input(By.id(prefix + "passwordHint")).clearField().sendKeys(passwordHint);
        input(By.id(prefix + "firstName")).clearField().sendKeys(firstName);
        input(By.id(prefix + "lastName")).clearField().sendKeys(lastName);
        input(By.id(prefix + "email")).clearField().sendKeys(email);
        input(By.id(prefix + "phoneNumber")).clearField().sendKeys(phoneNumber);
        input(By.id(prefix + "website")).clearField().sendKeys(website);
        input(By.id(prefix + "address")).clearField().sendKeys(address);
        input(By.id(prefix + "city")).clearField().sendKeys(city);
        input(By.id(prefix + "province")).clearField().sendKeys(province);
        input(By.id(prefix + "postalCode")).clearField().sendKeys(postalCode);
 
        select(By.id(prefix + "country")).selectByVisibleText(country.trim());
    }

    @Override
    public void clickSignup() {
        input(By.id("signupForm:save")).click();
    }

    @Override
    public String getSuccessMessages() {
        return div(By.id("successMessages")).getText().trim();
    }
}
