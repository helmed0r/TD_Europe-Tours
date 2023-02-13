package com.tr.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders extends TestBase {

    @DataProvider(name="sortingdata")
    public Object[][] SortingDataFeed(){
        return new Object[][] {
                {"prasc"},
                {"prdesc"},
                {"lenasc"},
                {"lendesc"},
                {"rec"},
                {"deals"},
                {"popularity"},
                {"ppdasc"},
                {"ppddesc"}
        };
    }
}
