Feature: Gestion du panier
  En tant qu'utilisateur de SauceDemo,
  Je veux pouvoir ajouter et supprimer des produits du panier,
  Afin de vérifier que la gestion du panier fonctionne correctement.

  Scenario:  Ajout d'un produit au panier
    Given je suis connecté en tant que "standard_user" avec le mot de passe "secret_sauce"
    When J'ajoute un produit au panier
    Then le produit devrait etre visible dans le panier
    And Le badge du panier devrait afficher "1"

  Scenario: Suppression d'un produit du panier
    Given je suis connecté en tant que "standard_user" avec le mot de passe "secret_sauce"
    When J'ajoute un produit au panier
    Then Le badge du panier devrait afficher "1"
    When je supprime le produit ajoute
    Then Le badge du panier ne devrait rien affiché



  Scenario: Ajout et suppression partielle des produits au panier
    Given je suis connecté en tant que "standard_user" avec le mot de passe "secret_sauce"
    When J'ajoute tous les produits disponibles au panier
    Then Le badge du panier devrait afficher "6"
    And Je vais dans le panier
    When Je supprime 2 produits du panier
    Then Le badge du panier devrait afficher "4"

  Scenario: Tri des produits par prix (bas à haut)
    Given je suis connecté en tant que "standard_user" avec le mot de passe "secret_sauce"
    When Je trie les produits par prix "bas à haut"
    Then Les produits devraient être affichés dans l'ordre "croissant des prix"

  Scenario: Tri des produits par prix (haut à bas)
    Given je suis connecté en tant que "standard_user" avec le mot de passe "secret_sauce"
    When Je trie les produits par prix "haut à bas"
    Then Les produits devraient être affichés dans l'ordre "décroissant des prix"







