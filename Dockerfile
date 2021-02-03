FROM openjdk:11-jdk-slim AS BUILD
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY . $APP_HOME
RUN ./gradlew clean build

FROM openjdk:11-jre-slim
ENV APP_HOME=/usr/app
COPY --from=BUILD  $APP_HOME/gogo-api/build/libs/gogo-api.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
