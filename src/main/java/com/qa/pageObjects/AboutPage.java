package com.qa.pageObjects;

import com.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends BaseTest {

    @FindBy(className="nav-navigation-list")
    WebElement aboutPageNavigation;

    public AboutPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean validateAboutPage()
    {
        return aboutPageNavigation.isDisplayed();
    }
}
