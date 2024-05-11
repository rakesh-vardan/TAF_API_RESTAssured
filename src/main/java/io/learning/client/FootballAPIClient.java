package io.learning.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class FootballAPIClient {

    private final RequestSpecification specification;

    @Autowired
    public FootballAPIClient(@Qualifier("footballRequestSpecification") RequestSpecification specification) {
        this.specification = specification;
    }

    public Response getTeamSpecificData(String teamId) {
        return given().spec(specification)
                .when().get("/teams/" + teamId);
    }

    public Response getAllTeamsData() {
        return given().spec(specification)
                .when().get("/teams");
    }
}
