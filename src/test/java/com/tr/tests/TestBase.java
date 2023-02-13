package com.tr.tests;

import com.tr.data.AppSettings;
import com.tr.pages.PageBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

    public WebDriver driver;

    public Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void init() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(AppSettings.DOMAIN);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p) {
        logger.info("Test start " + m.getName());
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: test method " + result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: Test method " + result.getMethod().getMethodName());
            String screen = "src/test/resources/screenshots/screen-" + (System.currentTimeMillis()/1000%3600) + ".png";
            new PageBase(driver, logger).takeScreenshot(screen);
        }
        logger.info("Stop test: " + result.getMethod().getMethodName());
    }



}
