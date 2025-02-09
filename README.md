# soumare98-Test_Selenium_Cucumber

# 🛒 Test automatisé de l'application SauceDemo avec Selenium & Cucumber

Ce projet contient des tests automatisés pour l'application **SauceDemo** en utilisant **Selenium, Cucumber, et Maven**.

## 📌 Technologies utilisées
- **Java**
- **Selenium WebDriver**
- **Cucumber**
- **Maven**
- **JUnit**

## 🚀 Installation et Exécution

### 1️⃣ **Cloner le projet**
```bash
git clone https://github.com/soumare98/Test_Selenium_Cucumber.git
```
- **cd SosDemo** pour le projet avec la méthode classique
- **cd TestCucumber** avec Cucumber et Selenium

### 2️⃣ **Exécuter tous les tests**
```bash
mvn test
```

### 3️⃣ **Exécuter les tests avec un rapport HTML**
```bash
mvn test -D cucumber.plugin="html:target/rapport.html"
```

### 4️⃣ **Exécuter le script sur Windows et linux **
```cmd
run_test.bat
```

```linux
chmod +x run_tests.sh
./run_tests.sh

```

### 5️⃣ **Exécuter certains tests avec selenium **
```bash
mvn test -D cucumber.filter.tags="@LoginSucces or @MotDePassInvalid"
mvn test -Dcucumber.features="src/test/resources/features/nom du feature" 


