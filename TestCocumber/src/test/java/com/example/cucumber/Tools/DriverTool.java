package com.example.cucumber.Tools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverTool {

    static public WebDriver driver;
   static public  WebDriverWait wait;


    static public void setupDriver(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }
    static public WebDriver getDriver(){
        return driver;
    }

    public static void quitDriver(){
        if (driver!=null) {
            driver.quit();
            driver = null;
        }
    }


}
