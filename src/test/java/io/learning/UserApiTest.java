package io.learning;

import io.learning.client.ApiClient;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserApiTest extends BaseTest {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private ResponseSpecification responseSpecification;

    @Test
    void testGetUser() {
        Response response = apiClient.getUser(1);
        response.then().spec(responseSpecification);
    }

    @Test
    void testCreateUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "John Doe");
        user.put("email", "john.doe@example.com");

        Response response = apiClient.createUser(user);
        assertEquals(201, response.statusCode());
    }

    @Test
    void testUpdateUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Jane Doe");
        user.put("email", "jane.doe@example.com");

        Response response = apiClient.updateUser(1, user);
        response.then().spec(responseSpecification);
    }

    @Test
    void testDeleteUser() {
        Response response = apiClient.deleteUser(1);
        response.then().spec(responseSpecification);
    }
}
