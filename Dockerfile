# ====== Build ======
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw \
 && sed -i 's/\r$//' mvnw \
 && sed -i 's/\r$//' .mvn/wrapper/maven-wrapper.properties \
 && ./mvnw -v

COPY src ./src

RUN ./mvnw -B -DskipTests clean package

# ====== Runtime ======
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
