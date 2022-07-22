package com.qa.listener;

import com.qa.base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureReportListenerNG extends BaseTest implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult){

        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value= "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver)
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value= "{0}", type = "text/plain")
    public static  String saveTextLog(String message)
    {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failure Happened at method : " + getTestMethodName(result));
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).driver;
        if(driver instanceof WebDriver)
        {
            System.out.println("Screenshot captured for test case : " + getTestMethodName(result));
            saveScreenshotPNG(driver);
        }

        saveTextLog(getTestMethodName(result) + "failed screenshot taken");
    }
}

