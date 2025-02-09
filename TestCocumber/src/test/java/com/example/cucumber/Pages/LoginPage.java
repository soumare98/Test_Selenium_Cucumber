package com.example.cucumber.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement usernameElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(id = "login-button")
    private WebElement loginButtonElement;

    @FindBy(className = "error")
    private WebElement errorMessageElement;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void saisirUserName(String username) {
        usernameElement.sendKeys(username);
    }

    public void saisirPassword(String password) {
        passwordElement.sendKeys(password);
    }

    public void cliqueSurLogin() {
        loginButtonElement.click();
    }

    public WebElement getMessageErreur() {
        return this.errorMessageElement;
    }
}
