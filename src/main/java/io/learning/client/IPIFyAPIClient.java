package io.learning.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class IPIFyAPIClient {

    private final RequestSpecification specification;

    @Autowired
    public IPIFyAPIClient(@Qualifier("ipifyRequestSpecification") RequestSpecification specification) {
        this.specification = specification;
    }

    public Response getIpAddress() {
        return given().spec(specification)
                .queryParam("format", "json")
                .when().get();
    }
}
