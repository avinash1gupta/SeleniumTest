package com.qa.base;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.util.Properties;
        import java.util.concurrent.TimeUnit;
        import com.qa.util.TestUtil;
        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.apache.log4j.Logger;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.testng.annotations.DataProvider;

public class BaseTest {
    Logger logger = Logger.getLogger(BaseTest.class);

    public static WebDriver driver;
    public static Properties prop;


    public BaseTest(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/"
                    + "qa/util/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            logger.info("Config file not found");
        } catch (IOException e) {
            logger.info("Config file not found");
        }
    }


    public static void initialization(){
        String browserName = prop.getProperty("browser");

        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));

    }
}
