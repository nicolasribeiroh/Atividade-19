@echo off
REM Atividade 20 - sobe API (8081) e Frontend (8080) ao mesmo tempo
REM Coloque este arquivo na pasta que contem Projeto3aAPI e produtos-frontend
echo === 1/2 Subindo a API na porta 8081 ===
start "API - porta 8081" cmd /k "cd /d %~dp0Projeto3aAPI && mvn spring-boot:run"
echo Aguardando a API iniciar (40s)...
timeout /t 40 /nobreak
echo === 2/2 Subindo o Frontend na porta 8080 ===
start "FRONTEND - porta 8080" cmd /k "cd /d %~dp0produtos-frontend && mvn spring-boot:run"
timeout /t 25 /nobreak
start http://localhost:8080
