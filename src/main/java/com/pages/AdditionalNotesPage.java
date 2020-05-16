package com.pages;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class AdditionalNotesPage extends BaseClass {

    public AdditionalNotesPage()
    {
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 60).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Is there anything else that the Plumber needs to know?')]")
    WebElement notesPageTitle;

    @FindBy(how = How.XPATH, using = "//textarea[@data-test='step_4']")
    WebElement additionalNotes;

    WebDriverWait wait = new WebDriverWait(driver, 100);


    public TimePeriodPage addNotes(String notes) throws IOException {
        if(notesPageTitle.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(additionalNotes));
            additionalNotes.clear();
            additionalNotes.sendKeys(notes);
            getScreenShot(driver,"AdditionalNotesEntered_");
            clickNextBtn();
        }
        return new TimePeriodPage();
    }


}

