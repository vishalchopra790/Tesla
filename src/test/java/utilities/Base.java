package utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Base {
    private static Logger log = LogManager.getLogger(Base.class.getName());

    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected Actions actions;
    protected Pages pages;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = DriverUtil.getDriver();
        log.info(ConfigReader.getProperty("browser") + " launched");
        DriverUtil.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        DriverUtil.getDriver().manage().window().maximize();
        softAssert = new SoftAssert();
        actions = new Actions(driver);
        pages = new Pages();
    }


    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        softAssert.assertAll();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Using this method if I run my tests in class level not with maven
            String screenshotLocation = BrowserUtil.getScreenshot(result.getName());
        }
            DriverUtil.closeDriver();
            log.info("browser closed");
        }

    }




