package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.base.BaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class HomePage extends BaseClass {

    public HomePage()
    {
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 60).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @FindBy(how = How.XPATH, using = "//form[@class='hero__form'] //input[@name='postal_code_input']")
    WebElement location;

    @FindBy(how = How.XPATH, using = "//button[@class='button button--branded blue go']")
    WebElement goButton;

    public ProblemPage search(String userLocation) throws IOException {
        location.clear();
        location.sendKeys(userLocation);
        location.sendKeys(Keys.ARROW_DOWN);
        getScreenShot(driver,"Location Entered_");
        goButton.click();
        clickNextBtn();
        return new ProblemPage();
    }

}
