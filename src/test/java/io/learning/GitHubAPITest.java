package io.learning;

import io.learning.client.GitHubAPIClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GitHubAPITest extends BaseTest {

    private final String USER_NAME = "rakesh-vardan";

    @Autowired
    GitHubAPIClient gitHubAPIClient;

    @Test
    void testVerifyUserDetails() {
        Response userResponse = gitHubAPIClient.getUser(USER_NAME);

        assertEquals(200, userResponse.statusCode());
        assertEquals(USER_NAME, userResponse.jsonPath().getString("login"));
        assertEquals("Rakesh Vardan", userResponse.jsonPath().getString("name"));
        assertEquals("https://rakeshvardan.com/about",
                userResponse.jsonPath().getString("blog"));
        assertEquals("Software Engineer [Test]",
                userResponse.jsonPath().getString("bio"));
    }

    @Test
    void testCreateAndDeleteRepository() {
        String repoName = "my-dummy-repo-test";

        // Create a new repository & verify if its created
        Response createResponse = gitHubAPIClient.createRepository(repoName);
        assertEquals(201, createResponse.statusCode());
        assertEquals(repoName, createResponse.jsonPath().getString("name"));

        // Verify that the repository is present
        Response getResponse = gitHubAPIClient.getRepository(repoName, this.USER_NAME);
        assertEquals(200, getResponse.statusCode());
        assertEquals(repoName, getResponse.jsonPath().getString("name"));

        // Delete the repository
        Response deleteResponse = gitHubAPIClient.deleteRepository(repoName, this.USER_NAME);
        assertEquals(204, deleteResponse.statusCode());
    }
}
