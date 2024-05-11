package io.learning.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Service
public class UsersAPIClient {

    private static final String USERS = "/users/";
    private final RequestSpecification specification;

    @Autowired
    public UsersAPIClient(@Qualifier("usersRequestSpecification") RequestSpecification specification) {
        this.specification = specification;
    }

    public Response getUsers() {
        return given(specification).get(USERS);
    }

    public Response getUser(int id) {
        return given(specification).get(USERS + id);
    }

    public Response createUser(Map<String, Object> user) {
        return given(specification).body(user).post(USERS);
    }

    public Response updateUser(int id, Map<String, Object> user) {
        return given(specification).body(user).put(USERS + id);
    }

    public Response deleteUser(int id) {
        return given(specification).delete(USERS + id);
    }
}
