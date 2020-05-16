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

public class ActionPage extends BaseClass {

    public ActionPage()
    {
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 60).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'What do you need done?')]")
    WebElement actionPageTitle;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Replace')]//preceding-sibling::div")
    WebElement replace;

    WebDriverWait wait = new WebDriverWait(driver, 100);


    public ProblemDescriptionPage selectAction(String actionChoice) throws IOException {
        if(actionPageTitle.isDisplayed())
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Installation')]")));
            int optionsSize = checkboxOptions.size();
            int checkboxSize = checkboxes.size();
            Assert.assertEquals(optionsSize,checkboxSize);
            for(int i=0; i<optionsSize; i++)
            {
                String val = checkboxOptions.get(i).getText();
                if(val.equalsIgnoreCase(actionChoice))
                {
                    checkboxes.get(i).click();
                    getScreenShot(driver,"ActionSelected_");
                    WebElement selectedCheckBox = driver.findElement(By.xpath("//div[contains(@class, 'checkBoxIsActive')]"));
                    activeCheckbox.add(selectedCheckBox);
                }
                else
                { System.out.println("No Match for Element " + i + "!"); }
            }
            if(activeCheckbox.size()>0)
                clickNextBtn();
        }
        return new ProblemDescriptionPage();
    }

}
