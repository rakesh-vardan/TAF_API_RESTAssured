package io.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.learning.client.UsersAPIClient;
import io.learning.model.User;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class UsersAPITest extends BaseTest {

    @Autowired
    private UsersAPIClient usersAPIClient;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    @Qualifier("usersResponseSpecification")
    private ResponseSpecification responseSpecification;

    @Test
    void testGetUser() {
        Response response = usersAPIClient.getUser(1);
        response.then().spec(responseSpecification);
    }

    @Test
    void testCreateUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "John Doe");
        user.put("email", "john.doe@example.com");

        Response response = usersAPIClient.createUser(user);
        assertEquals(201, response.statusCode());
    }

    @Test
    void testUpdateUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Jane Doe");
        user.put("email", "jane.doe@example.com");

        Response response = usersAPIClient.updateUser(1, user);
        response.then().spec(responseSpecification);
    }

    @Test
    void testDeleteUser() {
        Response response = usersAPIClient.deleteUser(1);
        response.then().spec(responseSpecification);
    }

    @Test
    void testUserSchema() {
        Response response = usersAPIClient.getUser(1);
        assertThat(response.getBody().asString(),
                matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @Test
    void testGetUserBody() {
        Response response = usersAPIClient.getUser(1);
        User actualUserResponse = response.as(User.class);
        User expectedUserResponse = this.getExpectedUserResponse();
        assertEquals(actualUserResponse, expectedUserResponse);
    }

    private User getExpectedUserResponse() {
        Resource resource = resourceLoader.getResource("classpath:user-data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        String content;
        User expectedUserResponse = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(resource.getURI())));
            expectedUserResponse = objectMapper.readValue(content, User.class);
        } catch (IOException e) {
            log.error("Error while reading file", e);
        }
        return expectedUserResponse;
    }
}
