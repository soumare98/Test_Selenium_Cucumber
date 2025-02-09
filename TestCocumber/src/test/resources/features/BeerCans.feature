Feature: Connexion à l'application SauceDemo
  En tant qu'utilisateur,
  Je veux tester différents scénarios de connexion,
  Afin de vérifier le bon fonctionnement du login.

  Background:
    Given je suis sur la page "https://www.saucedemo.com/"

  @LoginSucces
  Scenario: Connexion réussie avec des identifiants valides
    When je saisi le username "standard_user"
    And je saisi le password "secret_sauce"
    And je clique sur Login
    Then j accede mon dashboard

  @MotDePassInvalid
  Scenario: Connexion échouée avec un mot de passe incorrect
    When je saisi le username "standard_user"
    And je saisi le password "wrong_password"
    And je clique sur Login
    Then un message d erreur s affiche

  @UsernameInvalid
  Scenario: Connexion échouée avec un nom d'utilisateur incorrect
    When je saisi le username "wrong_user"
    And je saisi le password "secret_sauce"
    And je clique sur Login
    Then un message d erreur s affiche

  @ChampsVide
  Scenario: Connexion échouée avec des champs vides
    When je saisi le username ""
    And je saisi le password ""
    And je clique sur Login
    Then un message d erreur s affiche
