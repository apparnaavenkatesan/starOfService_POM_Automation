package com.pages;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.*;

public class ProblemPage extends BaseClass {

    public ProblemPage() {
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 60).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }


    public WebDriverWait wait = new WebDriverWait(driver, 100);


    @FindBy(xpath = "//div[contains(text(),'The problems are to do with which of the following things?')]")
    WebElement problemPageHeading;

//    @FindAll(@FindBy(how = How.XPATH, using = "//input[@type='checkbox']//following-sibling::div[1]"))
//    List<WebElement> checkboxes;
//
//    @FindAll(@FindBy(how = How.XPATH, using = "//input[@type='checkbox']//following-sibling::div[2]"))
//    List<WebElement> checkboxOptions;
//
//    List<WebElement> activeCheckbox = new ArrayList<>();


    public ActionPage selectProblem(String probItem) throws IOException {
        if(problemPageHeading.isDisplayed())
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Other']")));
            int optionsSize = checkboxOptions.size();
            int checkboxSize = checkboxes.size();
            Assert.assertEquals(optionsSize,checkboxSize);
            for(int i=0; i<optionsSize; i++)
            {
                String val = checkboxOptions.get(i).getText();
                if(val.equalsIgnoreCase(probItem))
                {
                    checkboxes.get(i).click();
                    getScreenShot(driver,"ProblemItemSelected_01_");
                    WebElement selectedCheckBox = driver.findElement(By.xpath("//div[contains(@class, 'checkBoxIsActive')]"));
                    activeCheckbox.add(selectedCheckBox);
                }
                else
                { System.out.println("No Match for Element " + i + "!"); }
            }
            if(activeCheckbox.size()>0)
                clickNextBtn();
        }
        return new ActionPage();
    }
}
            
