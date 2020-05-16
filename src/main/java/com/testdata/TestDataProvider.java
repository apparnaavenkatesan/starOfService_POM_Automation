package com.testdata;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name="TestDataProvider")
    public static Object[][] getDataMethod()
    {
        return new Object[][] {{"Chennai", "Tap", "Replace", "Leak in a pipe", "This is a Sample Text", "5"}};

    }
}
