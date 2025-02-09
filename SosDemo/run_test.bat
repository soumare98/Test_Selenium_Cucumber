@echo off
set PROJECT_DIR=%CD%

cd %PROJECT_DIR%
echo 📌 Exécution des tests avec Maven...

mvn test -D cucumber.plugin="html:target/rapport.html"

if %ERRORLEVEL% == 0 (
    echo ✅ Tests réussis ! Rapport généré dans target/rapport.html
) else (
    echo ❌ Échec des tests...
    exit /b 1
)
