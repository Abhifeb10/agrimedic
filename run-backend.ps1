# Agri-Medic Backend Launcher Script
Write-Host "========================================" -ForegroundColor Green
Write-Host "🌾 Starting Agri-Medic Backend Service..." -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green

# Ensure Java 21 is configured
$env:JAVA_HOME = "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.2.0.2\jbr"
Write-Host "Using Java: $env:JAVA_HOME" -ForegroundColor Cyan

# Change to backend directory
Set-Location -Path "$PSScriptRoot\agrimedic_backend"

# Launch Spring Boot with Maven
Write-Host "Executing mvn spring-boot:run..." -ForegroundColor Yellow
mvn spring-boot:run
