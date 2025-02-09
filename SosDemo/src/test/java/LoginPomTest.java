
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class LoginPomTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void TestLogin(){
        String[] usernames = {"standard_user","problem_user","locked_out_user","performance_glitch_user","error_user","visual_user"};

        for (String username : usernames){
            loginPage.saisirUsername(username);
            loginPage.saisirPassword("secret_sauce");
            loginPage.cliqueSurBoutonLogin();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("app_logo")));


            Assertions.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/LoginData.csv", numLinesToSkip = 1) // Indiquer le chemin du fichier CSV
    public void testLogin(String username, String password) {
        // Test avec chaque couple username/password fourni dans le fichier CSV
        loginPage.saisirUsername(username);
        loginPage.saisirPassword(password);
        loginPage.cliqueSurBoutonLogin();

        // Attente que la page se charge après la connexion
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("app_logo")));

        // Vérification que l'utilisateur est redirigé vers la page d'inventaire
        Assertions.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }


    @Tag("Test2")
    @Test
    public void testAjoutProduitAuPanier() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();


        ProductPage productPage = new ProductPage(driver);


        productPage.ajouterProduitAuPanier();


        Assertions.assertTrue(productPage.isProduitAjoute(), "Le produit n'a pas été ajouté au panier");


        Assertions.assertEquals("1", productPage.getCartBadge(), "Le panier ne contient pas 1 produit");

      //  driver.quit();

    }
    @Tag("Test3")
    @Test
    public void testAccesPanier() throws InterruptedException {
        // Connexion
        LoginPage loginPage = new LoginPage(driver);
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        // Ajout d'un produit au panier
        ProductPage productPage = new ProductPage(driver);
        productPage.ajouterProduitAuPanier();

        // Aller au panier
        productPage.allerAuPanier();

        // Vérifier que le produit est bien dans le panier
        CartPage cartPage = new CartPage(driver);
        Assertions.assertEquals(1, cartPage.getNombreProduitsDansPanier(), "Le panier ne contient pas 1 produit");

        Thread.sleep(50000);

        // Ferme le navigateur après le test
        driver.quit();
    }

@Tag("Test4")
    @Test
    public void testCheckout() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();


        ProductPage productPage = new ProductPage(driver);
        productPage.ajouterProduitAuPanier();
        productPage.allerAuPanier();


        CartPage cartPage = new CartPage(driver);
        cartPage.procederAuPaiement();


        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.saisirFirstName("bachir");
        checkoutPage.saisirLastName("Soumare");
        checkoutPage.saisirPostalCode("94200");
        checkoutPage.cliquerSurContinue();

        // Vérification : on arrive sur la page suivante (par exemple, la page de récapitulatif)
        String urlAttendue = "https://www.saucedemo.com/checkout-step-two.html";
        Assertions.assertEquals(urlAttendue, driver.getCurrentUrl(), "La page de checkout n'a pas avancé !");

        // Ajoute une pause pour voir la page avant de fermer
        Thread.sleep(50000);

        // Fermer le navigateur
        driver.quit();
    }
    @Tag("Test5")
    @Test
    public void testCheckoutComplete() throws InterruptedException {

        // Connexion
        LoginPage loginPage = new LoginPage(driver);
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        // Ajout d'un produit au panier
        ProductPage productPage = new ProductPage(driver);
        productPage.ajouterProduitAuPanier();
        productPage.allerAuPanier();

        // Aller au checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.procederAuPaiement();

        // Remplir les informations de checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.saisirFirstName("Bachir");
        checkoutPage.saisirLastName("SOUMARE");
        checkoutPage.saisirPostalCode("94200");
        checkoutPage.cliquerSurContinue();

       EndPage checkoutOverviewPage = new EndPage(driver);
        checkoutOverviewPage.cliquerSurFinish();


        String urlAttendue = "https://www.saucedemo.com/checkout-complete.html";
        Assertions.assertEquals(urlAttendue, driver.getCurrentUrl(), "L'achat n'a pas été complété !");

        // Pause pour voir la page avant de fermer
        Thread.sleep(50000);

        // Fermer le navigateur
        driver.quit();
    }
    @Tag("Test6")
    @Test
    public void testAjoutEtSuppressionPartielleProduitsAuPanier() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        // Connexion à l'application
        loginPage.saisirUsername("standard_user");
        loginPage.saisirPassword("secret_sauce");
        loginPage.cliqueSurBoutonLogin();

        ProductPage productPage = new ProductPage(driver);

        // Ajouter plusieurs produits au panier
        productPage.ajouterTousLesProduitsAuPanier();

        // Attendre 3 secondes après l'ajout
        Thread.sleep(30000);

        // Vérifier que le panier contient 6 produits
        Assertions.assertEquals("6", productPage.getCartBadge(), "Le panier ne contient pas le bon nombre de produits");

        // Aller au panier
        productPage.allerAuPanier();

        // Attendre 2 secondes avant la suppression
        Thread.sleep(15000);

        // Supprimer 2 produits du panier
        productPage.supprimerProduits(2);

        // Attendre 2 secondes après la suppression
        Thread.sleep(15000);

        // Vérifier que le panier contient maintenant 4 produits
        Assertions.assertEquals("4", productPage.getCartBadge(), "Le panier ne contient pas le bon nombre de produits après suppression");

        // Fermer le navigateur (si besoin)
        // driver.quit();
    }



}
