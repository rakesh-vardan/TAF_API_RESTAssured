# Stage 1: Download dependencies
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /usr/src/myapp
COPY pom.xml .
RUN mvn dependency:go-offline

# Stage 2: Build the application and download allure
COPY . .
RUN mvn clean test \
    && curl -o allure-2.15.0.tgz -Ls https://github.com/allure-framework/allure2/releases/download/2.15.0/allure-2.15.0.tgz \
    && tar -zxvf allure-2.15.0.tgz -C /opt/ \
    && ln -s /opt/allure-2.15.0/bin/allure /usr/bin/allure \
    && rm allure-2.15.0.tgz

# Stage 3: Create the final image
FROM openjdk:17-jdk
COPY --from=build /usr/src/myapp/target /usr/app/target
COPY --from=build /usr/bin/allure /usr/bin/allure
WORKDIR /usr/app
CMD allure serve -h 0.0.0.0 -p 8090 target/allure-results