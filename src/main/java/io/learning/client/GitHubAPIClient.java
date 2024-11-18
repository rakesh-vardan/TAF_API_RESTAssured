package io.learning.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Service
public class GitHubAPIClient {

    private final RequestSpecification specification;

    @Autowired
    public GitHubAPIClient(@Qualifier("gitHubRequestSpecification") RequestSpecification specification) {
        this.specification = specification;
    }

    public Response getUser(String userName) {
        return given(specification).get("/users/" + userName);
    }

    public Response createRepository(String repoName) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", repoName);
        requestBody.put("description", "This is a new repository");
        requestBody.put("homepage", "https://github.com");
        requestBody.put("private", false);
        requestBody.put("has_issues", true);
        requestBody.put("has_projects", true);
        requestBody.put("has_wiki", true);

        return given()
                .spec(specification)
                .body(requestBody)
                .when().post("/user/repos");
    }

    public Response getRepository(String repoName, String username) {
        return given()
                .spec(specification)
                .when().get("/repos/" + username + "/" + repoName);
    }

    public Response deleteRepository(String repoName, String username) {
        return given()
                .spec(specification)
                .when().delete("/repos/" + username + "/" + repoName);
    }

}
