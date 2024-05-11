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
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static io.restassured.RestAssured.oauth2;

@SpringBootConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "io.learning")
public class TestConfig {

    @Value("${users.api.baseUri}")
    private String usersApiBaseUri;

    @Value("${ipify.api.baseUri}")
    private String ipifyApiBaseUri;

    @Value("${github.api.baseUri}")
    private String gitHubApiBaseUri;

    @Value("${github.api.token}")
    private String githubApiToken;

    @Value("${postman-echo.api.baseUri}")
    private String postmanEchoApiBaseUri;

    @Value("${football.api.baseUri}")
    private String footballApiBaseUri;

    @Value("${football.api.token}")
    private String footballApiToken;

    @Bean
    public RequestSpecification usersRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(usersApiBaseUri)
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Bean
    public ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Content-Type",
                        "application/json; charset=utf-8")
                .build();
    }

    @Bean
    public RequestSpecification ipifyRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ipifyApiBaseUri)
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Bean
    public RequestSpecification gitHubRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(gitHubApiBaseUri)
                .setAuth(oauth2(githubApiToken))
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Bean
    public RequestSpecification postmanEchoRequestSpecWithoutAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(postmanEchoApiBaseUri)
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Bean
    public RequestSpecification postmanEchoRequestSpecWithAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(postmanEchoApiBaseUri)
                .addHeader("Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==")
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Bean
    public RequestSpecification footballRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(footballApiBaseUri)
                .setBasePath("/v4")
                .addHeader("X-Auth-Token", footballApiToken)
                .addFilter(new AllureRestAssured())
                .build();
    }
}
