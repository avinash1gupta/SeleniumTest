package com.qa.pageObjects;

import com.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HomePage extends BaseTest {
    @FindBy(id="shopping_cart_container")
    WebElement cartButton;

    @FindBy(id="react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id="reset_sidebar_link")
    WebElement resetLink;

    @FindBy(id="inventory_sidebar_link")
    WebElement inventoryLink;

    @FindBy(id="about_sidebar_link")
    WebElement aboutLink;

    @FindBy(id="logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(id="react-burger-cross-btn")
    WebElement crossMenu;


    @FindBy(className="active_option")
    WebElement activeOption;

    @FindBy(className="product_sort_container")
    WebElement selectOption;

    @FindBy(className="inventory_item_name")
    List<WebElement> itemName;

    @FindBy(className="inventory_item_price")
    List<WebElement> itemPrice;

    @FindBy(xpath="//*[@class=\"inventory_item_price\"]/following-sibling::button")
    List<WebElement> addToCartButton;

    @FindBy(className="shopping_cart_badge")
    WebElement countOfCart;

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public boolean validateHomePageIsDisplayed(){
        return cartButton.isDisplayed();
    }

    public boolean validateMenuIsDisplayed(){
        return menuButton.isDisplayed();
    }

    public boolean validateCountOfCartIsDisplayed(){
        try
        {
            return countOfCart.isDisplayed();
        }catch (org.openqa.selenium.NoSuchElementException ex)
        {
            return false;
        }
    }

    public boolean validateMenuItemsIsDisplayed(){
        menuButton.click();
        return inventoryLink.isDisplayed();
    }
    public boolean validateMenuAboutIsDisplayed(){

        menuButton.click();
        return aboutLink.isDisplayed();
    }
    public boolean validateMenuLogOffIsDisplayed(){
        menuButton.click();
        return logoutLink.isDisplayed();
    }
    public boolean validateMenuResetIsDisplayed(){
        menuButton.click();
        return resetLink.isDisplayed();
    }
    public boolean validateMenuCrossIsDisplayed(){
        menuButton.click();
        return crossMenu.isDisplayed();
    }

    public String getActiveOption()
    {
        return activeOption.getText();
    }

    public void setActiveOption(int index)
    {
        Select options = new Select(selectOption);
        options.selectByIndex(index);
    }

    public List<String> sortItemByName(int order)
    {
        List<String> itemNames = new LinkedList<>();

        for( WebElement a : itemName)
            itemNames.add(a.getText());
        if(order==0)
        {
            Collections.sort(itemNames);
        }
        else {
            Collections.sort(itemNames,Collections.reverseOrder());
        }
        return itemNames;
    }

    public List<String> sortItemByPrice(int order)
    {
        List<String> itemPrices = new LinkedList<>();

        for( WebElement a : itemPrice)
            itemPrices.add(a.getText());
        if(order==0)
        {
            Collections.sort(itemPrices);
        }
        else {
            Collections.sort(itemPrices,Collections.reverseOrder());
        }
        return itemPrices;
    }


    public void addToCart(int index, String oper)
    {
        if(addToCartButton.get(index).getText().trim().equalsIgnoreCase(oper))
        {
            addToCartButton.get(index).click();
        }

    }

    public String getCartCount()
    {
        return countOfCart.getText();
    }

    public List<String> getPrice()
    {
        List<String> itemPrices = new LinkedList<>();

        for( WebElement a : itemPrice)
            itemPrices.add(a.getText());
        return itemPrices;
    }

    public void clickReset()
    {
        resetLink.click();
        crossMenu.click();
    }

    public LoginPage clickLogoff()
    {
        logoutLink.click();
        return new LoginPage();
    }

    public AboutPage clickAbout()
    {
        aboutLink.click();
        return new AboutPage();
    }

    public CartPage clickCountOfCart()
    {
        countOfCart.click();
        return new CartPage();
    }

}


