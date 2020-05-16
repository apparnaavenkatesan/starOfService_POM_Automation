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

public class ProblemDescriptionPage extends BaseClass {

    public ProblemDescriptionPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'What problem(s) do you have?')]")
    WebElement probDescPageTitle;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Leak in a pipe')]/preceding-sibling::div")
    WebElement leakInPipe;

    WebDriverWait wait = new WebDriverWait(driver, 100);

    public AdditionalNotesPage chooseProblem(String probDescValue) throws IOException {
        if(probDescPageTitle.isDisplayed())
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Other']")));
            int optionsSize = checkboxOptions.size();
            int checkboxSize = checkboxes.size();
            Assert.assertEquals(optionsSize,checkboxSize);
            for(int i=0; i<optionsSize; i++)
            {
                String val = checkboxOptions.get(i).getText();
                if(val.equalsIgnoreCase(probDescValue))
                {
                    checkboxes.get(i).click();
                    getScreenShot(driver,"ProblemDescriptionSelected_");
                    WebElement selectedCheckBox = driver.findElement(By.xpath("//div[contains(@class, 'checkBoxIsActive')]"));
                    activeCheckbox.add(selectedCheckBox);
                }
                else
                { System.out.println("No Match for Element " + i + "!"); }
            }
            if(activeCheckbox.size()>0)
                clickNextBtn();
        }
        return new AdditionalNotesPage();
    }

}
