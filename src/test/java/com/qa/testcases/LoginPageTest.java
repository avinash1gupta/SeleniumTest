package com.qa.testcases;

import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.util.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;

public class LoginPageTest extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;

    public LoginPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
    }

    @Test(priority=1)
    public void loginPageTitleTest(){
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title,"Swag Labs");
    }


    @Test(priority=2)
    public void sauceLabsLogoTest(){
        boolean flag = loginPage.validateSauceDemoLogo();

        Assert.assertTrue(flag);
    }

    @Test(priority=3, description = " Successful Login Attempted")
    public void loginTest(){
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        boolean flag = homePage.validateHomePageIsDisplayed();
        Assert.assertTrue(flag);
    }

    @Test(priority=4)
    public void loginFailTest(){
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password")+1);
        boolean flag = loginPage.validateErrorInLogin();
        Assert.assertTrue(flag);

    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

//    @DataProvider
//
//    public Object[][] Authentication() throws Exception{
//
//        Object[][] testObjArray = ExcelUtil.getTableArray(System.getProperty("user.dir")+ "/src/main/java/resources/","Sheet1");
//
//        return (testObjArray);
//
//    }





}

