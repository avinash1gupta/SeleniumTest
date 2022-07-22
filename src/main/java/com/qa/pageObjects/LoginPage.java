package com.qa.pageObjects;

import com.qa.base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseTest {

    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(xpath="//input[@type='submit']")
    WebElement loginBtn;

    @FindBy(className="login_logo")
    WebElement sauceDemoLogo;

    @FindBy(className="error-button")
    WebElement errorMessageButton;



    //Initializing the Page Objects:
    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    //Actions:
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public boolean validateSauceDemoLogo(){
        return sauceDemoLogo.isDisplayed();
    }

    public boolean validateErrorInLogin(){
        return errorMessageButton.isDisplayed();
    }

    public HomePage login(String un, String pwd) {
        username.sendKeys(un);
        password.sendKeys(pwd);
        loginBtn.click();
        return new HomePage();
    }

}
