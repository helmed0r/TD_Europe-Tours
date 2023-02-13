package com.tr.tests.dTests;

import com.tr.pages.HomePage;
import com.tr.pages.d.EuropePage;
import com.tr.tests.TestBase;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class EuropeTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        HomePage hp = new HomePage(driver,logger);
        hp.acceptCookieMessage();
        hp.hideWelcomeMessage();
        hp.opendEuropePage();
    }

    @Test(testName = "TR_EU_02", priority = 1)
    public void sortByMostViewedWithNoFiltersTest()
    {
        Assert.assertTrue( new EuropePage(driver,logger).sortBy("rec").checkToursSortedByReviews() );
    }

    @Test(testName = "TR_EU_05", dataProvider = "sortingdata", priority = 0)
    public void checkSortingIsTheSameBetweenLanguages(String sortBy)
    {
        EuropePage page = new EuropePage(driver,logger);
        List<String> enTours = page.switchToLang("English").sortBy(sortBy).getListOfTours();
        List<String> deTours = page.switchToLang("Deutsch").sortBy(sortBy).getListOfTours();
        List<String> nlTours = page.switchToLang("Nederlands").sortBy(sortBy).getListOfTours();

        Assert.assertEquals(enTours, deTours);
        Assert.assertEquals(enTours, nlTours);
    }

    @AfterMethod(enabled = false)
    public void checkPostconditions() {
        new EuropePage(driver,logger).opendHomePage();
    }
}
