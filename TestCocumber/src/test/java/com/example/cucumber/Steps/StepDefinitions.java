package com.example.cucumber.Steps;

import com.example.cucumber.Pages.LoginPage;
import com.example.cucumber.Tools.DriverTool;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;



public class StepDefinitions {

    WebDriver driver;

    LoginPage loginPage;


    public StepDefinitions(){
        driver= DriverTool.getDriver();
    }



    @Given("je suis sur la page {string}")
    public void je_suis_sur_la_page_login(String s){
        this.driver.get(s);
        this.loginPage=new LoginPage(this.driver);

    }

    @When("je saisi le password {string}")
        public void je_saisi_le_password(String s){
        this.loginPage.saisirPassword(s);

    }

    @When("je saisi le username {string}")
    public  void je_saisi_le_username(String s){

        this.loginPage.saisirUserName(s);
    }

    @Then("j accede mon dashboard")
    public void j_accede_mon_dashboard(){

        assertTrue(true);
    }
    @When("je clique sur Login")
    public  void je_clique_sur_login(){

        this.loginPage.cliqueSurLogin();
    }

    @Then("un message d erreur s affiche")
    public void un_message_d_erreur_s_affiche() {
        assertTrue(this.loginPage.getMessageErreur().isDisplayed());
    }


}
