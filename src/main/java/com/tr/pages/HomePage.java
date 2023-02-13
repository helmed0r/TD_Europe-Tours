package com.tr.pages;


import com.tr.pages.d.EuropePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.List;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver, Logger logger) {
        super(driver, logger);
    }

    @FindBy(xpath = "/html/body/header/div/ul/li[1]/a")
    WebElement destinationsLink;

    @FindBy(xpath = "//a[@href='/d/europe']")
    List<WebElement> linksEurope;

    public EuropePage opendEuropePage() {
        Actions actions = new Actions(driver);

        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(5));
        waiting.until(ExpectedConditions.elementToBeClickable(destinationsLink));

        actions.moveToElement(destinationsLink).perform();

        waiting.until( ExpectedConditions.elementToBeClickable( linksEurope.get(0) ) );

        linksEurope.get(0).click();

        return new EuropePage(driver, logger);
    }
}
