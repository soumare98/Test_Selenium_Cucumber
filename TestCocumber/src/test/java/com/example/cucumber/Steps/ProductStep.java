package com.example.cucumber.Steps;

import com.example.cucumber.Pages.LoginPage;
import com.example.cucumber.Pages.ProductPage;

import com.example.cucumber.Tools.DriverTool;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

;

public class ProductStep {

    private WebDriver driver;

    private LoginPage loginPage;

    private ProductPage productPage;

    public ProductStep() {
        driver= DriverTool.getDriver();
        productPage = new ProductPage(driver);
    }



@Etantdonné("je suis connecté en tant que {string} avec le mot de passe {string}")
    public void je_suis_connecte(String username,String password){
    productPage = new ProductPage(driver);
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
         loginPage.saisirUserName(username);
        loginPage.saisirPassword(password);
        loginPage.cliqueSurLogin();




    }
    @Quand("J'ajoute un produit au panier")
    public void j_ajoute_produit_au_panier(){
    productPage=new ProductPage(driver);
    productPage.ajouterProduitAuPanier();
    }

    @Quand("J'ajoute tous les produits disponibles au panier")
    public void j_ajoute_tous_les_produits(){
        productPage=new ProductPage(driver);
        productPage.ajouterTousLesProduitsAuPanier();
    }

    @Quand("je supprime le produit ajoute")
    public void j_supprime_le_produit_ajoute() {
        productPage.removeProduitPanier();
        productPage.waitForCartUpdate();
    }

    @Quand("Je supprime {int} produits du panier")
    public void je_supprime_produits_du_panier(int nombre) {
        if (productPage == null) {
            productPage = new ProductPage(driver);
        }
        productPage.supprimerProduits(nombre);
    }


    @Alors("le produit devrait etre visible dans le panier")
    public void verifier_produit_dans_panier(){
        Assertions.assertTrue(productPage.isProduitAjoute());

    }


    @Et("Le badge du panier devrait afficher {string}")
    public void verifier_badge_panier(String Count){
    Assertions.assertEquals(Count,productPage.getCartBadge());
    }
    @Et("Je vais dans le panier")
    public void jeVaisDansLePanier() {
        productPage.allerAuPanier();
    }

    @Et("Le badge du panier ne devrait rien affiché")
    public void verifier_badge_panier_vide() {
        productPage.getCartBadge();
            }

            @Quand("je clique sur le panier")
            public void verifier_panier(){
        productPage.cliquepanier();
            }


    @Quand("J'ajoute {int} produits différents au panier")
    public void j_ajoute_produits_differents_au_panier(int nombreProduits) {
        for (int i = 0; i < nombreProduits; i++) {
            productPage.ajouterProduitsAuPanier(i);
        }
    }

    @Quand("Je trie les produits par prix {string}")
    public void trier_produits_par_prix(String ordre) {
        productPage.trierProduitsParPrix(ordre);
    }

    @Quand("Je filtre les produits par nom {string}")
    public void filtrer_produits_par_nom(String ordre) {
        productPage.trierProduitsParNom(ordre);
    }


    @Alors("Les produits devraient être affichés dans l'ordre {string}")
    public void verifier_ordre_produits(String ordre) {
        Assertions.assertTrue(productPage.verifierOrdreProduits(ordre), "Les produits ne sont pas dans l'ordre attendu");
    }







}
