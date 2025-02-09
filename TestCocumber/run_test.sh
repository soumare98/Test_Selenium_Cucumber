#!/bin/bash

# Définir le dossier du projet
PROJECT_DIR="C:\Users\pc\Desktop\Test_Selenium_Cucumber\TestCocumber"

cd "$PROJECT_DIR" || exit

# Exécuter tous les tests et générer un rapport HTML
mvn test -D cucumber.plugin="html:target/rapport.html"


if [ $? -eq 0 ]; then
    echo "✅ Tests réussis ! Rapport généré dans target/rapport.html"
else
    echo "❌ Échec des tests..."
    exit 1
fi
