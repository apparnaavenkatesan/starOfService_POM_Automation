package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseClass
{
    public static WebDriver driver;
    public static Properties prop;

    @FindAll(@FindBy(how = How.XPATH, using = "//input[@type='checkbox']//following-sibling::div[1]"))
    public List<WebElement> checkboxes;

    @FindAll(@FindBy(how = How.XPATH, using = "//input[@type='checkbox']//following-sibling::div[2]"))
    public List<WebElement> checkboxOptions;

    public List<WebElement> activeCheckbox = new ArrayList<>();


    @FindBy(how = How.XPATH, using = "//button[@data-test='next_button']")
   public static WebElement nextButton;


    public static void clickNextBtn()
    {
        nextButton.click();
    }


    @BeforeSuite
    public static void readPropertyFile() throws IOException
    {
        prop = new Properties();
        FileInputStream ip = new FileInputStream("src/main/java/config.properties");
        prop.load(ip);
        FileUtils.deleteDirectory(new File("target/Screenshots"));
    }


    public static void initBrowser()
    {
        String browserChoice = prop.getProperty("browser");

        if(browserChoice.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        if(browserChoice.equals("chrome")){
            WebDriverManager.chromedriver().browserVersion("81.0.4044.138").setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));


    }


        public static void getScreenShot(WebDriver driver, String screenshotName) throws IOException
        {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/target/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        }

    public static String addDaysToCurrentDate(int numberOfDays)
        {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd,yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, numberOfDays);
        System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
         }



    @AfterSuite
    public static void quitDriver()
    {
        driver.quit();
    }


}

