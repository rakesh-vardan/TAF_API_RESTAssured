package io.learning.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = "io.learning")
public class TestConfig {

    @Value("${api.baseUri}")
    private String baseUri;

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    @Bean
    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Bean
    public ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Content-Type", "application/json; charset=utf-8")
                .build();
    }
}
