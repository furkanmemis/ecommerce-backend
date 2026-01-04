# ---------- Build Stage ----------
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Pom ve kaynakları kopyala
COPY pom.xml .
COPY src ./src

# Maven ile JAR oluştur
RUN mvn clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Build stage'den JAR'ı kopyala
COPY --from=build /app/target/*.jar app.jar

# Servisi tüm network interface'lerinde dinle
ENV JAVA_OPTS="-Dserver.address=0.0.0.0"

ENTRYPOINT ["java","-jar","app.jar"]
