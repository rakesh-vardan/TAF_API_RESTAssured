package io.learning.client;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class PostmanAPIClient {

    private static final String BASIC_AUTH = "/basic-auth";
    private final RequestSpecification specWithAuth;
    private final RequestSpecification specWithOutAuth;

    @Autowired
    public PostmanAPIClient(@Qualifier("postmanEchoRequestSpecWithAuth") RequestSpecification specWithAuth,
                            @Qualifier("postmanEchoRequestSpecWithoutAuth") RequestSpecification specWithOutAuth) {
        this.specWithAuth = specWithAuth;
        this.specWithOutAuth = specWithOutAuth;
    }

    public Response getDataNoAuth() {
        return given(specWithOutAuth).get(BASIC_AUTH);
    }

    public Response getDataWithBasicAuth() {
        return given(specWithAuth).get(BASIC_AUTH);
    }

    public Response getCookies(Cookies cookies) {
        return given()
                .spec(specWithOutAuth)
                .cookies(cookies)
                .when().get("/cookies");
    }
}
