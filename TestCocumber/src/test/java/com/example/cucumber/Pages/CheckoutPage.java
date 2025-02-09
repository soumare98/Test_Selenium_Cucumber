package com.example.cucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    @FindBy(className = "error")
    private WebElement errorMessageElement;

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

    public void laisserChampsVides() {
        firstNameElement.clear();
        lastNameElement.clear();
        postalCodeElement.clear();
        continueButtonElement.click();

    }

    public void saisirCodePostalInvalide(String codePostal) {
        postalCodeElement.sendKeys(codePostal);
    }

    public WebElement isErrorMessageDisplayed() {
        return this.errorMessageElement;

    }
}
