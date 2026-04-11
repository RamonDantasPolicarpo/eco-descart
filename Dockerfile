# Buldando a iamgem docker
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom.xml e o código fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto gerando o .jar (ignorando testes para ser mais rápido)
RUN mvn clean package -DskipTests

# Execução (Imagem final enxuta)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia apenas o .jar gerado no estágio anterior
COPY --from=build /app/target/eco-descart-1.0.0.jar app.jar

# Expõe a porta que o Spring Boot usa
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]