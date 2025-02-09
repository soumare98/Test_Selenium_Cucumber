package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "cart_button")
    private List<WebElement> removeButtons;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void ajouterProduitAuPanier() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }


    public boolean isProduitAjoute() {
        return wait.until(ExpectedConditions.visibilityOf(removeButton)).isDisplayed();
    }

    public String getCartBadge() {
        return wait.until(ExpectedConditions.visibilityOf(cartBadge)).getText();
    }

    public void allerAuPanier() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public void ajouterTousLesProduitsAuPanier() {
        for (WebElement button : addToCartButtons) {
            wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        }
    }

    public void supprimerProduits(int nombre) {
        int count = 0;
        for (WebElement button : removeButtons) {
            if (count < nombre) {
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                count++;
            } else {
                break;
            }
        }
    }

}
