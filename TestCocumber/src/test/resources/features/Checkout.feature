Feature: Checkout d'un produit sur SauceDemo

  Scenario: L'utilisateur complète un achat avec succès
    Given l'utilisateur est sur la page de login
    When il se connecte avec l'identifiant "standard_user" et le mot de passe "secret_sauce"
    And il ajoute un produit au panier
    And il accède au panier
    And il procède au paiement
    And il saisit ses informations de checkout:
      | prénom | nom     | code postal |
      | Bachir | SOUMARE | 94200       |
    And il valide son achat
    Then l'achat doit être complété avec succès



  Scenario: Checkout avec des informations manquantes
    Given l'utilisateur est sur la page de login
    When il se connecte avec l'identifiant "standard_user" et le mot de passe "secret_sauce"
    And il ajoute un produit au panier
    And il accède au panier
    And il procède au paiement
    And Je laisse les champs de checkout vides
    And je clique sur continue
    Then Un message d'erreur devrait s'afficher


