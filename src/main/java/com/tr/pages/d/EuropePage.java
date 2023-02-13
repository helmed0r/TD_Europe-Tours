package com.tr.pages.d;


import com.tr.pages.HomePage;
import com.tr.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public class EuropePage extends PageBase {

    public EuropePage(WebDriver driver, Logger logger) {
        super(driver, logger);
    }

    @FindBy(css = ".js-aa-serp-sort.aa-serp-sort.js-aa-dropdown.aa-dropdown")
    List<WebElement> sortBySelectClass;



    public EuropePage sortBy(String sortBy) {
        logger.info("Sorting by "+sortBy);

        WebElement sortBySelect = sortBySelectClass.get(0);
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(5));
        waiting.until(ExpectedConditions.elementToBeClickable(sortBySelect));
        Select select = new Select(sortBySelect);
        select.selectByValue(sortBy);
        waiting.until(ExpectedConditions. elementToBeClickable(tourHeaders.get(0)));
        wait(1000);
        logger.info("Sorted by "+sortBy);
        return this;
    }

    @FindBy(xpath = "//div/a/span[contains(text(),'review')]")
    List<WebElement> reviewsList;

    public boolean checkToursSortedByReviews() {

        logger.info("Checking tours are srted by review desc");
        for (int i=0; i<=reviewsList.size()-2; i++ )
        {
            String currentReviewsSpan = reviewsList.get(i).getText();
            String nextReviewsSpan = reviewsList.get(i+1).getText();
            String separator = "\\s";

            int currentReviewsCount = Integer.parseInt( currentReviewsSpan.split(separator)[0] );
            int nextReviewsCount = Integer.parseInt( nextReviewsSpan.split(separator)[0] );

            if ( currentReviewsCount < nextReviewsCount )
            {
                logger.error( String.format("Failed comparing review counts. Reviews %s is larger than %s", currentReviewsSpan, nextReviewsSpan) );
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @FindBy(xpath = "//div/div/a[.='EN']")
    WebElement homeLink;

    public HomePage opendHomePage() {
        scrollToFooter();
        click(homeLink);
        return new HomePage(driver,logger);
    }



    @FindBy(css = ".js-ao-header-navigation__item.js-profile.js-ao-header-desktop-nav.ao-header-desktop-nav.ao-header-navigation__language")
    List<WebElement> langLinkList;

    @FindBy(xpath = "//nav/ul/li/a[.='English']")
    WebElement enLink;

    @FindBy(xpath = "//nav/ul/li/a[.='Deutsch']")
    WebElement deLink;

    @FindBy(xpath = "//nav/ul/li/a[.='Nederlands']")
    WebElement nlLink;

    @FindBy(xpath = "/html/body/header/div/ul/li[5]")
    WebElement languageSelection;

    @FindBy(css = ".ao-common-social-login-popup__close.js-ao-common-social-login-popup__close")
    List<WebElement> closeWelcomeList;


    public EuropePage switchToLang(String language) {
        logger.info("Switching to language "+language);

        Actions actions = new Actions(driver);
        actions.moveToElement(languageSelection).perform();

        WebElement langLink = languageSelection.findElement(By.xpath(String.format("//a[.='%s']", language)));

        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(7));
        waiting.until(ExpectedConditions.elementToBeClickable(langLink));
        click(langLink);
        waiting.until(ExpectedConditions.elementToBeClickable(sortBySelectClass.get(0)));
        wait(2000);
        return this;
    }


    @FindBy(xpath = "//h4[@data-id]")
    List<WebElement> tourHeaders;

    public List<String> getListOfTours() {
        wait(1500);
        List<String> tourIDs = new ArrayList<>();

        for (WebElement tourHeader : tourHeaders) {
            tourIDs.add( tourHeader.getAttribute("data-id") );
        }

        return tourIDs;

    }

}
