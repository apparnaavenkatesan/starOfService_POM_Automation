package com.pages;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class TimeSlotPage extends BaseClass {

    public TimeSlotPage()
    {
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 60).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'What time do you need the Plumber?')]")
    WebElement timePageTitle;

    @FindBy(how = How.XPATH, using = "//select[@data-test='step_time']")
    WebElement timeSelectDropdown;

    @FindBy(how = How.XPATH, using = "//input[@data-test='step_duration']")
    WebElement timeDuration;

    WebDriverWait wait = new WebDriverWait(driver, 100);

    public EmailPage selectTime(String timeDurationVal) throws IOException {
        if(timePageTitle.isDisplayed())
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-test='step_duration']")));
            Select sel = new Select(timeSelectDropdown);
            List<WebElement> options = sel.getOptions();
            sel.selectByVisibleText(options.get(1).getText());
            timeDuration.sendKeys(timeDurationVal);
            getScreenShot(driver,"TimeSlotChosen_");
            clickNextBtn();
        }
        return new EmailPage();
    }


}
