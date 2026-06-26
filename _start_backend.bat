@echo off
cd /d "D:\twice-market-master\backend"
set JAVA_HOME=D:\develop\java\jdk-21
call mvnw.cmd spring-boot:run -DskipTests
