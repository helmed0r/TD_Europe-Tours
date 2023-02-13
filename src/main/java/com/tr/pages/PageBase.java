package com.tr.pages;

import com.google.common.io.Files;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PageBase {

    public WebDriver driver;
    public Logger logger;

    public PageBase(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        PageFactory.initElements(driver, this);
    }

    public void click(@NotNull WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void clickWithJSExecutor(WebElement element, int x, int y) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        this.wait(500);
        click(element);
    }

    public void typeWithJSExecutor(WebElement element, String text, int x, int y) {
        if (text != null) {
            clickWithJSExecutor(element, x, y);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeScreenshot(String pathToFile) {

        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(pathToFile);

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hideWelcomeMessage() {
        logger.info("Running hideWelcomeMessage");

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.cookie = \"show_login_popup=hide;\"");

        } catch (Exception r) {
            System.out.println(r.toString());
        }
    }

    public void acceptCookieMessage () {

        logger.info("Running acceptCookieMessage");
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.cookie = \"cn_status=1\"");

        } catch (Exception e) {
            logger.error("Failed to set cookies");
        }
    }

}

