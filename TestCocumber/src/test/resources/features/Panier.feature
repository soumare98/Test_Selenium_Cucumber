Feature: Accès au panier

  Scenario: Vérification du contenu du panier
    Given Je suis connecté en tant que "standard_user" avec le mot de passe "secret_sauce"
    When j'ajoute un produit dans le  panier
    And J'accède à la page du panier
    Then Le panier devrait contenir exactement 1 produit

