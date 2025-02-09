package com.example.cucumber.Hooks;

import com.example.cucumber.Tools.DriverTool;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;

public class hooks {
    @Before
    public void setUp(){
        DriverTool.setupDriver();
    }

    public void tearDown(Scenario scenario){

        if (scenario.isFailed()) {
            try {

                WebDriver driver = DriverTool.getDriver();
                if (driver != null) {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Capture d'écran");
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la capture d'écran: " + e.getMessage());
            }
        }
        DriverTool.quitDriver();
    }
}
