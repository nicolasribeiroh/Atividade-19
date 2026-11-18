# Prompts para o GitHub Copilot Chat (VS Code)

Como usar: abra a pasta do projeto no VS Code, abra o Copilot Chat (Ctrl+Alt+I),
selecione o modo **Agent** (ou Edit) e cole o prompt. Revise as alterações antes de aceitar.

---------------------------------------------------------------------
## PROMPT 1 - Adaptar a MINHA API (Atividade 15) para a Atividade 18
(use se preferir manter o seu projeto em vez do Projeto3aAPI pronto)

Este é um projeto Spring Boot 3 (Java 17, Maven) com um CRUD de Produto usando Spring Data JPA e PostgreSQL.
Prepare o projeto para deploy no Render, sem mudar o nome dos pacotes existentes:
1. No pom.xml, adicione a dependência org.springframework.boot:spring-boot-starter-actuator (se não existir) e confirme que existem spring-boot-starter-data-jpa e org.postgresql:postgresql (scope runtime).
2. Reescreva src/main/resources/application.properties para: server.port=${PORT:8081}; spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/projeto3aapi}; spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}; spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:<mantenha a senha local que já está no arquivo>}; spring.jpa.hibernate.ddl-auto=update; spring.jpa.show-sql=true; management.endpoints.web.exposure.include=health,info,metrics; management.endpoint.health.show-details=always; management.info.env.enabled=true; e as propriedades info.app.name, info.app.description, info.app.version=1.0.0, info.app.author, info.app.ambiente=producao.
3. Crie a classe CorsConfig (anotada com @Configuration, implementando WebMvcConfigurer) no mesmo pacote do Controller, com registry.addMapping("/**"), allowedOriginPatterns("https://*.onrender.com", "http://localhost:*"), allowedMethods GET, POST, PUT, DELETE, OPTIONS, allowedHeaders("*") e allowCredentials(true).
4. Garanta que o Controller tenha GET /produtos, GET /produtos/{id}, POST /produtos, PUT /produtos/{id} e DELETE /produtos/{id}, retornando 404 quando o ID não existir.
5. Crie um Dockerfile na raiz em duas etapas: build com a imagem maven:3.9.6-eclipse-temurin-17 executando "mvn clean package -DskipTests" e runtime com eclipse-temurin:17-jdk-alpine copiando target/*.jar para app.jar, EXPOSE 8080 e ENTRYPOINT ["java","-jar","app.jar"].
6. Se existir um teste @SpringBootTest que exige banco, anote-o com @Disabled("Requer banco de dados ativo").
7. Garanta que o .gitignore contenha target/, .idea/, .vscode/ e *.iml.
Ao final, liste os arquivos alterados e o que foi feito em cada um.

---------------------------------------------------------------------
## PROMPT 2 - Criar o Maven Wrapper (mvnw) que está faltando

O meu projeto Spring Boot não tem os arquivos mvnw, mvnw.cmd e a pasta .mvn/wrapper.
Me diga o comando exato do Maven para gerar o Maven Wrapper nesta pasta (Windows PowerShell),
execute-o no terminal e depois confirme que ".\mvnw.cmd -v" funciona.
Não altere nenhum código-fonte.

(Comando esperado: mvn -N wrapper:wrapper)

---------------------------------------------------------------------
## PROMPT 3 - Frontend para a Atividade 19 (deploy no Render)

Este é um projeto Spring Boot que serve um frontend estático em src/main/resources/static (index.html, style.css, script.js).
1. No script.js, configure a URL da API assim: const API_RENDER = '<COLE AQUI A URL DA SUA API>/produtos'; const API_LOCAL = 'http://localhost:8081/produtos'; e defina API_BASE_URL usando API_LOCAL quando window.location.hostname for localhost ou 127.0.0.1, e API_RENDER nos demais casos. Garanta que todo o resto do script use API_BASE_URL.
2. Substitua o application.properties por: server.port=${PORT:8080}; spring.application.name=produtos-frontend; spring.web.resources.static-locations=classpath:/static/; spring.mvc.static-path-pattern=/**; spring.thymeleaf.enabled=false; spring.web.resources.cache.cachecontrol.max-age=3600; spring.web.resources.cache.cachecontrol.must-revalidate=true.
3. Crie o Dockerfile na raiz (build com maven:3.9.6-eclipse-temurin-17 e runtime com eclipse-temurin:17-jdk-alpine, EXPOSE 8080, ENTRYPOINT java -jar app.jar).
Liste o que foi alterado.

---------------------------------------------------------------------
## PROMPT 4 - Diagnosticar erro de build ou de deploy

Estou fazendo deploy de uma aplicação Spring Boot 3 (Java 17, Docker) no Render, conectada a um PostgreSQL do Render pelas variáveis SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME e SPRING_DATASOURCE_PASSWORD.
O deploy falhou com o log abaixo. Explique a causa em português simples, diga exatamente qual arquivo ou variável de ambiente devo corrigir e mostre o valor correto. Não invente valores de senha ou host.

<COLE AQUI AS ÚLTIMAS ~60 LINHAS DO LOG>

---------------------------------------------------------------------
## PROMPT 5 - Erro de CORS ou "Não foi possível conectar à API"

O meu frontend (<URL DO FRONT>) chama a API (<URL DA API>/produtos) usando fetch, e o navegador mostra este erro no Console (F12):
<COLE O ERRO>
Analise o CorsConfig.java da API e o script.js do frontend abertos neste workspace, aponte a causa e corrija. Mantenha os endpoints em /produtos.

---------------------------------------------------------------------
## PROMPT 6 - Atividade 20 (API na 8081 e frontend na 8080)

Tenho dois projetos Spring Boot neste workspace: a API (Projeto3aAPI) e o frontend (produtos-frontend).
Verifique e ajuste para rodar os dois juntos no meu computador:
1. Na API, o application.properties deve ter server.port=${PORT:8081} e apontar para o PostgreSQL local (jdbc:postgresql://localhost:5432/projeto3aapi).
2. No frontend, o application.properties deve ter server.port=${PORT:8080} e o script.js deve usar 'http://localhost:8081/produtos' quando rodar em localhost.
3. O CorsConfig da API deve permitir http://localhost:8080.
Depois me diga, em ordem, os comandos para subir primeiro a API e depois o frontend em dois terminais.
