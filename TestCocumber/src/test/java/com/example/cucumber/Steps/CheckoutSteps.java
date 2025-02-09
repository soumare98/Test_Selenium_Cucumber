package com.example.cucumber.Steps;

import com.example.cucumber.Hooks.hooks;
import com.example.cucumber.Pages.*;
import com.example.cucumber.Tools.DriverTool;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.ElementTraversal;

import java.time.Duration;

public class CheckoutSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private PanierPage cartPage;
    private CheckoutPage checkoutPage;
    private EndPage endPage;

public  CheckoutSteps(){
    this.driver= DriverTool.getDriver();
}

    @Given("l'utilisateur est sur la page de login")
    public void utilisateurSurPageLogin() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("il se connecte avec l'identifiant {string} et le mot de passe {string}")
    public void seConnecter(String username, String password) {
        loginPage.saisirUserName(username);
        loginPage.saisirPassword(password);
        loginPage.cliqueSurLogin();

        productPage = new ProductPage(driver);
    }

    @When("il ajoute un produit au panier")
    public void ajouterProduit() {
        productPage = new ProductPage(driver);
        productPage.ajouterProduitAuPanier();
    }

    @When("il accède au panier")
    public void accederAuPanier() {
        productPage.allerAuPanier();
    }

    @When("il procède au paiement")
    public void procederAuPaiement() {
        cartPage = new PanierPage(driver);
        cartPage.procederAuPaiement();
    }

    @When("il saisit ses informations de checkout:")
    public void saisirInfosCheckout(io.cucumber.datatable.DataTable dataTable) {
        checkoutPage = new CheckoutPage(driver);
        var data = dataTable.asMaps(String.class, String.class).get(0);
        checkoutPage.saisirFirstName(data.get("prénom"));
        checkoutPage.saisirLastName(data.get("nom"));
        checkoutPage.saisirPostalCode(data.get("code postal"));
    }
    @Et("je clique sur continue")
    public void cliqueContinue(){
        checkoutPage.cliquerSurContinue();
    }

    @When("il valide son achat")
    public void validerAchat() {
        checkoutPage.cliquerSurContinue();
        endPage = new EndPage(driver);
        endPage.cliquerSurFinish();
    }

    @Alors("l'achat doit être complété avec succès")
    public void verifierAchat() {
        String urlAttendue = "https://www.saucedemo.com/checkout-complete.html";
        Assertions.assertEquals(urlAttendue, driver.getCurrentUrl(), "L'achat n'a pas été complété !");
    }


    @When("Je laisse les champs de checkout vides")
    public void laisser_champs_vides() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.laisserChampsVides();
    }

    @When("Je saisis un code postal invalide {string}")
    public void saisir_code_postal_invalide(String codePostal) {
        checkoutPage.saisirCodePostalInvalide(codePostal);
    }

    @Then("Un message d'erreur devrait s'afficher")
    public void verifier_message_erreur() {
        Assertions.assertTrue(checkoutPage.isErrorMessageDisplayed().isDisplayed());
    }


}
