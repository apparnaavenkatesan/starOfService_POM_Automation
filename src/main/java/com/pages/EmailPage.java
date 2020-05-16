package com.pages;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class EmailPage extends BaseClass {

    public EmailPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@id='field-Where would you like us to notify you about new quotes received on your request?']")
    WebElement emailPageTitle;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Email address']")
    WebElement emailInputBox;

    WebDriverWait wait = new WebDriverWait(driver, 100);

    public void isEmailFieldPresent() throws IOException {
        if(emailPageTitle.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(emailInputBox));
            getScreenShot(driver,"EmailPage_");
            Assert.assertTrue(emailInputBox.isDisplayed());

        }

    }


}
