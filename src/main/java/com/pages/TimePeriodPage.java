package com.pages;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class TimePeriodPage extends BaseClass {

    public TimePeriodPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'When do you require plumbing?')]")
    WebElement timePeriodPageTitle;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'On a specific date')]/preceding-sibling::div")
    WebElement onASpecificDate_btn;

    WebDriverWait wait = new WebDriverWait(driver, 100);


    public DatePage selectTimePeriod() throws IOException {
        if (timePeriodPageTitle.isDisplayed())
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Other (I will need to confirm)')]")));
            onASpecificDate_btn.click();
            getScreenShot(driver,"TimePeriodSelected_");
            clickNextBtn();
        }
        return new DatePage();
    }

}
