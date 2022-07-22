package com.qa.testcases;

import com.qa.base.BaseTest;
import com.qa.pageObjects.AboutPage;
import com.qa.pageObjects.CartPage;
import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority=1)
    public void defaultActiveOptionTest(){
        String defaultOption = homePage.getActiveOption();
        Assert.assertEquals(defaultOption.trim(),"NAME (A TO Z)");
    }


    @Test(priority=2)
    public void changeNameDescOptionTest(){
        homePage.setActiveOption(1);
        String changedOption = homePage.getActiveOption();
        Assert.assertEquals(changedOption.trim(),"NAME (Z TO A)");
        List<String> descSortedItems = homePage.sortItemByName(1);
        Assert.assertEquals(descSortedItems.get(0),"Test.allTheThings() T-Shirt (Red)");

    }

    @Test(priority=3)
    public void changePriceHighToLowOptionTest(){
        homePage.setActiveOption(3);
        String changedOption = homePage.getActiveOption();
        Assert.assertEquals(changedOption.trim(),"PRICE (HIGH TO LOW)");
        List<String> descSortedItems = homePage.sortItemByPrice(1);
        Assert.assertEqualsNoOrder(descSortedItems,homePage.getPrice());

    }

    @Test(priority=4)
    public void changePriceLowToHighOptionTest(){
        homePage.setActiveOption(2);
        String changedOption = homePage.getActiveOption();
        Assert.assertEquals(changedOption.trim(),"PRICE (LOW TO HIGH)");
        List<String> descSortedItems = homePage.sortItemByPrice(0);
        Assert.assertEqualsNoOrder(descSortedItems,homePage.getPrice());
    }

    @Test(priority=5)
    public void addToCartTest(){
        homePage.setActiveOption(0);
        homePage.addToCart(0,"Add to cart");
        homePage.addToCart(1,"Add to cart");
        int count = Integer.parseInt(homePage.getCartCount());
        //Remove  Add to cart
        Assert.assertEquals(count,2);

    }
    @Test(priority=6)
    public void removeFromCartTest(){
        homePage.setActiveOption(0);
        homePage.addToCart(0,"Add to cart");
        homePage.addToCart(1,"Add to cart");
        homePage.addToCart(0,"Remove");
        homePage.addToCart(2,"Remove");
        int count = Integer.parseInt(homePage.getCartCount());
        Assert.assertEquals(count,1);

    }
    @Test(priority=7)
    public void cartCountTest(){
        homePage.addToCart(0,"Add to cart");
        homePage.addToCart(1,"Add to cart");
        int count = Integer.parseInt(homePage.getCartCount());
        Assert.assertEquals(count,2);
    }

    @Test(priority=8)
    public void restAppTest(){
        homePage.addToCart(0,"Add to cart");
        homePage.addToCart(1,"Add to cart");
        boolean flag = homePage.validateMenuResetIsDisplayed();
        Assert.assertTrue(flag);
        homePage.clickReset();
        Assert.assertFalse(homePage.validateCountOfCartIsDisplayed());
    }

    @Test(priority=9)
    public void logoffAppTest()
    {
        boolean flag = homePage.validateMenuLogOffIsDisplayed();
        Assert.assertTrue(flag);
        LoginPage loginPage = homePage.clickLogoff();
        Assert.assertTrue(loginPage.validateSauceDemoLogo());
    }

    @Test(priority=10)
    public void aboutMenuTest()
    {
        boolean flag = homePage.validateMenuAboutIsDisplayed();
        Assert.assertTrue(flag);
        AboutPage aboutPage = homePage.clickAbout();
        Assert.assertTrue(aboutPage.validateAboutPage());
    }

    @Test(priority=11)
    public void cartItemsLinkTest()
    {
        boolean flag = homePage.validateHomePageIsDisplayed();
        Assert.assertTrue(flag);
        homePage.addToCart(0,"Add to cart");
        homePage.addToCart(1,"Add to cart");
        CartPage cartPage = homePage.clickCountOfCart();
        Assert.assertTrue(cartPage.validateCartPage());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

