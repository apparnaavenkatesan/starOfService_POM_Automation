package com.testCases;

import com.base.BaseClass;
import com.pages.*;
import com.testdata.TestDataProvider;
import org.testng.annotations.Test;

public class TC001_StartRun  extends BaseClass {

    @Test(dataProvider = "TestDataProvider", dataProviderClass = TestDataProvider.class)
    public void mainTest (String locName, String problemItem, String actionValue, String problemDesc, String getAdditionalText,  String timePeriodValue)
    {
        initBrowser();
        HomePage homepage = new HomePage();
    try {
        homepage.search(locName)
                .selectProblem(problemItem)
                .selectAction(actionValue)
                .chooseProblem(problemDesc)
                .addNotes(getAdditionalText)
                .selectTimePeriod()
                .selectDate()
                .selectTime(timePeriodValue)
                .isEmailFieldPresent();
      } catch(Exception e)
    {
        System.out.println("Exception Caught! Error: " + e);
        e.printStackTrace();
    }


    }

}
