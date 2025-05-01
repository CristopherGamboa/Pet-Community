FROM openjdk:21

WORKDIR /app
COPY target/pet-community-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_J3JSZ128ZNNXBZ7W /app/oracle_wallet
EXPOSE 8081

ENTRYPOINT exec java -jar app.jar