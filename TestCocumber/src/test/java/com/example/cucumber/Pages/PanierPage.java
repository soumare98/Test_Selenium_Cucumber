package com.example.cucumber.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PanierPage {

    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    // Constructeur
    public PanierPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int getNombreProduitsDansPanier() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }


    public void procederAuPaiement() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }


}
