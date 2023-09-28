# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR da sua aplicação Spring Boot para o contêiner
COPY target/monitoramento-0.0.1-SNAPSHOT.jar /app/monitoramento-0.0.1-SNAPSHOT.jar

# Exponha a porta em que sua aplicação vai rodar (por exemplo, 8080)
EXPOSE 8082

# Comando para iniciar a aplicação quando o contêiner for executado
CMD ["java", "-jar", "monitoramento-0.0.1-SNAPSHOT.jar"]
