package com.example.cucumber.Steps;

import com.example.cucumber.Pages.LoginPage;
import com.example.cucumber.Pages.PanierPage;
import com.example.cucumber.Pages.ProductPage;
import com.example.cucumber.Tools.DriverTool;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PanierStep {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private PanierPage cartPage;
    private WebDriverWait wait;

    public PanierStep(){
        driver= DriverTool.getDriver();
        wait= new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Given("Je suis connecté en tant que {string} avec le mot de passe {string}")
    public void je_suis_connecte(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);

        loginPage.saisirUserName(username);
        loginPage.saisirPassword(password);
        loginPage.cliqueSurLogin();

        productPage = new ProductPage(driver);
    }

    @Quand("j'ajoute un produit dans le  panier")
    public void ajouter_le_produit_au_panier() {
        productPage.ajouterProduitAuPanier();
    }

    @Quand("J'accède à la page du panier")
    public void acceder_au_panier() {
        productPage.allerAuPanier();
        cartPage = new PanierPage(driver);
    }

    @Alors("Le panier devrait contenir exactement {int} produit")
    public void verifier_nombre_produits(int expectedCount) {
        int actualCount = cartPage.getNombreProduitsDansPanier();
        Assertions.assertEquals(expectedCount, actualCount,
                "Nombre de produits incorrect dans le panier");

    }
    @Quand("J'ajoute le produit {string} au panier")
    public void ajouter_produit_par_nom(String nomProduit) {
        productPage.ajouterProduitParNom(nomProduit);
    }

    @Quand("Je supprime tous les produits du panier")
    public void supprimer_tous_les_produits() {
        productPage.supprimerTousLesProduits();
    }

    @Alors("Le panier devrait être vide")
    public void verifier_panier_vide() {
        Assertions.assertTrue(productPage.isPanierVide(), "Le panier n'est pas vide");
    }

}
