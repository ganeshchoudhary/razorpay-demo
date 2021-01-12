FROM openjdk:8-jdk-alpine
WORKDIR /app
ARG JAR_NAME=*.jar
ARG JAR_FILE=target/${JAR_NAME}
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
