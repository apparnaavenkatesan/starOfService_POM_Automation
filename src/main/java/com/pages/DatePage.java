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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePage extends BaseClass {

    public DatePage() {
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, 100);

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'On what date?')]")
    WebElement datePageTitle;

    @FindBy(how = How.XPATH, using = "//td[contains(@class,'today')]")
    WebElement date_today;


    public TimeSlotPage selectDate() throws IOException {
        if (datePageTitle.isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOf(date_today));
            String dateToSelect = addDaysToCurrentDate(2);
            String dateForXpath = dateToSelect.split(",")[0];
            driver.findElement(By.cssSelector("[class*='CalendarMonth'] [aria-label*='" + dateForXpath + "']")).click();
            getScreenShot(driver, "DateSelected_");
            clickNextBtn();

        }
        return new TimeSlotPage();
    }



}
