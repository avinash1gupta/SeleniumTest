package com.qa.pageObjects;

import com.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {


    @FindBy(className="title")
    WebElement cartTitle;
    public CartPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean validateCartPage()
    {
        return cartTitle.isDisplayed();
    }
}
