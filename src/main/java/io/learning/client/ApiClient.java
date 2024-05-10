package io.learning.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Service
public class ApiClient {

    @Autowired
    private RequestSpecification specification;

    public Response getUsers() {
        return given(specification).get("/users");
    }

    public Response getUser(int id) {
        return given(specification).get("/users/" + id);
    }

    public Response createUser(Map<String, Object> user) {
        return given(specification).body(user).post("/users");
    }

    public Response updateUser(int id, Map<String, Object> user) {
        return given(specification).body(user).put("/users/" + id);
    }

    public Response patchUser(int id, Map<String, Object> user) {
        return given(specification).body(user).patch("/users/" + id);
    }

    public Response deleteUser(int id) {
        return given(specification).delete("/users/" + id);
    }
}
