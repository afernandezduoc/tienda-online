# Dockerfile
FROM openjdk:21-jdk

WORKDIR /app

COPY target/tiendaonline-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet_storeonline /app/oracle_wallet
EXPOSE 9090

CMD ["java", "-jar", "app.jar"]