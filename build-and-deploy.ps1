# ========================================================
# Build and Deploy script for Travel Booking Management System
# ========================================================

$ErrorActionPreference = "Stop"

$jdkPath = "C:\Program Files\Java\jdk-21\bin"
$javac = "$jdkPath\javac.exe"
$jar = "$jdkPath\jar.exe"
$tomcatHome = "C:\Apache\Tomcat\apache-tomcat-10.1.57-windows-x64\apache-tomcat-10.1.57"
$servletJar = "$tomcatHome\lib\servlet-api.jar"
$mysqlJar = "C:\Users\gowth\.m2\repository\com\mysql\mysql-connector-j\8.0.33\mysql-connector-j-8.0.33.jar"

$warDir = "target\war\travel-booking"

Write-Host "1. Compiling Java classes..." -ForegroundColor Cyan
New-Item -ItemType Directory -Force -Path "target\classes" | Out-Null
$sources = (Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java").FullName
& $javac -cp "$servletJar;$mysqlJar" -d "target\classes" $sources

Write-Host "2. Assembling WAR structure..." -ForegroundColor Cyan
if (Test-Path $warDir) { Remove-Item -Recurse -Force $warDir }
New-Item -ItemType Directory -Force -Path "$warDir\WEB-INF\classes" | Out-Null
New-Item -ItemType Directory -Force -Path "$warDir\WEB-INF\lib" | Out-Null
New-Item -ItemType Directory -Force -Path "$warDir\css" | Out-Null

Copy-Item -Recurse -Force "target\classes\*" "$warDir\WEB-INF\classes\"
Copy-Item -Force $mysqlJar "$warDir\WEB-INF\lib\"
Copy-Item -Force "src\main\webapp\WEB-INF\web.xml" "$warDir\WEB-INF\"
Copy-Item -Force "src\main\webapp\css\style.css" "$warDir\css\"

Write-Host "3. Creating target\travel-booking.war..." -ForegroundColor Cyan
Set-Location $warDir
& $jar -cvf "..\..\travel-booking.war" *
Set-Location "..\..\.."

Write-Host "4. Deploying to Tomcat webapps..." -ForegroundColor Cyan
$webapps = "$tomcatHome\webapps"
Copy-Item -Force "target\travel-booking.war" "$webapps\"

Write-Host "`nSUCCESS! Application is live at: http://localhost:8080/travel-booking/" -ForegroundColor Green
