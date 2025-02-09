package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private WebDriver driver;


    @FindBy(id = "first-name")
    private WebElement firstNameElement;

    @FindBy(id = "last-name")
    private WebElement lastNameElement;

    @FindBy(id = "postal-code")
    private WebElement postalCodeElement;

    @FindBy(id = "continue")
    private WebElement continueButtonElement;

    // Constructeur
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void saisirFirstName(String firstName) {
        firstNameElement.sendKeys(firstName);
    }

    public void saisirLastName(String lastName) {
        lastNameElement.sendKeys(lastName);
    }

    public void saisirPostalCode(String postalCode) {
        postalCodeElement.sendKeys(postalCode);
    }

    public void cliquerSurContinue() {
        continueButtonElement.click();
    }



}
