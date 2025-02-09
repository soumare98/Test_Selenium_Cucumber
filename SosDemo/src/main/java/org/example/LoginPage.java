package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(xpath = "//input[@type='submit' and @class='submit-button btn_action' and @id='login-button']")
    private WebElement loginButtonElement;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void saisirUsername(String username) {
        usernameElement.sendKeys(username);
    }

    public void saisirPassword(String password) {
        passwordElement.sendKeys(password);
    }

    public void cliqueSurBoutonLogin() {
        loginButtonElement.click();
    }
}
