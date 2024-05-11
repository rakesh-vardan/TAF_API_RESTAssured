package io.learning;

import io.learning.client.UsersAPIClient;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAPIWithOrderTest extends BaseTest {

    @Autowired
    private UsersAPIClient usersAPIClient;

    @Autowired
    private ResponseSpecification responseSpecification;

    @Test
    @Order(1)
    void testGetUser() {
        Response response = usersAPIClient.getUser(1);
        response.then().spec(responseSpecification);
    }

    @Test
    @Order(2)
    void testCreateUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "John Doe");
        user.put("email", "john.doe@example.com");

        Response response = usersAPIClient.createUser(user);
        assertEquals(201, response.statusCode());
    }

    @Test
    @Order(3)
    void testUpdateUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Jane Doe");
        user.put("email", "jane.doe@example.com");

        Response response = usersAPIClient.updateUser(1, user);
        response.then().spec(responseSpecification);
    }

    @Test
    @Order(4)
    void testDeleteUser() {
        Response response = usersAPIClient.deleteUser(1);
        response.then().spec(responseSpecification);
    }
}
