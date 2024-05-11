package io.learning;

import io.learning.client.UsersAPIClient;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersAPITest extends BaseTest {

    @Autowired
    private UsersAPIClient usersAPIClient;

    @Autowired
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
}
