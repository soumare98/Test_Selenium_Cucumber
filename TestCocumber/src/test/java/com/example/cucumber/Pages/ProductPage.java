package com.example.cucumber.Pages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public void ajouterProduitsAuPanier(int index) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(addToCartButtons));
            System.out.println("Nombre de boutons trouvés : " + addToCartButtons.size());

            if (addToCartButtons.isEmpty()) {
                throw new IllegalArgumentException("Aucun bouton trouvé");
            }

            if (index >= 0 && index < addToCartButtons.size()) {
                WebElement button = addToCartButtons.get(index);
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                System.out.println("Produit ajouté au panier.");
            } else {
                throw new IllegalArgumentException("Index de produit invalide : " + index);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout au panier : " + e.getMessage());
            e.printStackTrace();
        }
    }



    public boolean isProduitAjoute() {
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.visibilityOf(removeButton));
            return removeBtn.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Le produit n'est pas visible dans le panier (pas de bouton 'Remove').");
            return false;
        }
    }

    public void removeProduitPanier() {
           wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();

    }

    public void cliquepanier(){
        wait.until(ExpectedConditions.elementToBeClickable(cartBadge)).click();
    }


    public String  getCartBadge() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(cartBadge)).getText();
        } catch (TimeoutException e) {
            return "0"; // Retourne "0" si le badge n'est pas visible
        }
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
    public void waitForCartUpdate() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(cartBadge));
        } catch (TimeoutException e) {
            // Le badge peut rester visible avec "0"
            wait.until(d -> "0".equals(cartBadge.getText()));
        }
    }

    public boolean isCartBadgeVisible() {
        try {
            return cartBadge.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void ajouterProduitParNom(String nomProduit) {
        WebElement produit = driver.findElement(By.xpath("//div[text()='" + nomProduit + "']/ancestor::div[@class='inventory_item']//button"));
        wait.until(ExpectedConditions.elementToBeClickable(produit)).click();
    }

    public void supprimerTousLesProduits() {
        for (WebElement button : removeButtons) {
            wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        }
    }

    public boolean isPanierVide() {
        try {
            return driver.findElement(By.className("cart_empty_label")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void trierProduitsParPrix(String ordre) {
        Select triSelect = new Select(driver.findElement(By.className("product_sort_container")));
        if (ordre.equalsIgnoreCase("bas à haut") || ordre.equalsIgnoreCase("croissant des prix")) {
            triSelect.selectByValue("lohi"); // Tri par prix bas à haut
        } else if (ordre.equalsIgnoreCase("haut à bas") || ordre.equalsIgnoreCase("décroissant des prix")) {
            triSelect.selectByValue("hilo"); // Tri par prix haut à bas
        } else {
            throw new IllegalArgumentException("Ordre de tri non reconnu : " + ordre);
        }

        // Attendre que le tri soit appliqué
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            List<WebElement> elementsPrix = driver.findElements(By.className("inventory_item_price"));
            return !elementsPrix.isEmpty();
        });
    }
    public void trierProduitsParNom(String ordre) {
        Select triSelect = new Select(driver.findElement(By.className("product_sort_container")));
        if (ordre.equalsIgnoreCase("A à Z") || ordre.equalsIgnoreCase("alphabétique")) {
            triSelect.selectByValue("az");
        } else if (ordre.equalsIgnoreCase("Z à A") || ordre.equalsIgnoreCase("alphabétique inverse")) {
            triSelect.selectByValue("za");
        } else {
            throw new IllegalArgumentException("Ordre de tri non reconnu : " + ordre);
        }

        // Attendre que le tri soit appliqué
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            List<WebElement> elementsNoms = driver.findElements(By.className("inventory_item_name"));
            return !elementsNoms.isEmpty();
        });
    }

    public boolean verifierOrdreProduits(String ordre) {
        // Attendre que le tri soit appliqué
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            List<WebElement> elementsPrix = driver.findElements(By.className("inventory_item_price"));
            return !elementsPrix.isEmpty();
        });

        // Extraire les prix
        List<WebElement> elementsPrix = driver.findElements(By.className("inventory_item_price"));
        List<Double> prix = elementsPrix.stream()
                .map(e -> {
                    String textePrix = e.getText().replace("$", "").trim();
                    System.out.println("Prix extrait : " + textePrix); // Log pour débogage
                    return Double.parseDouble(textePrix);
                })
                .collect(Collectors.toList());

        System.out.println("Liste des prix : " + prix); // Log pour débogage

        // Vérifier l'ordre
        if (ordre.equalsIgnoreCase("bas à haut") || ordre.equalsIgnoreCase("croissant des prix")) {
            boolean estTrie = Ordering.natural().isOrdered(prix);
            System.out.println("Ordre croissant vérifié : " + estTrie); // Log pour débogage
            return estTrie;
        } else if (ordre.equalsIgnoreCase("haut à bas") || ordre.equalsIgnoreCase("décroissant des prix")) {
            boolean estTrie = Ordering.natural().reverse().isOrdered(prix);
            System.out.println("Ordre décroissant vérifié : " + estTrie); // Log pour débogage
            return estTrie;
        } else {
            throw new IllegalArgumentException("Ordre non reconnu : " + ordre);
        }
    }
}
