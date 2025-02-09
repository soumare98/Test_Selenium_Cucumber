package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EndPage {

    private WebDriver driver;

    @FindBy(id = "cancel")
    private WebElement cancelButtonElement;

    @FindBy(id = "finish")
    private WebElement finishButtonElement;


    public EndPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void cliquerSurCancel() {
        cancelButtonElement.click();
    }

    public void cliquerSurFinish() {
        finishButtonElement.click();
    }
}
