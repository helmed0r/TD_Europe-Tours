package com.tr.data;

import com.tr.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class AppSettings extends PageBase {

    public AppSettings(WebDriver driver, Logger logger) {
        super(driver,logger);
    }

    public static final String DOMAIN = "https://www.tourradar.com";
}
