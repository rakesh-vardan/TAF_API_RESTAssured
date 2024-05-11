package io.learning;

import io.learning.client.PostmanAPIClient;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanAPITest extends BaseTest {

    @Autowired
    private PostmanAPIClient postmanAPIClient;

    @Test
    void testWithOutAnyAuth() {
        Response response = postmanAPIClient.getDataNoAuth();
        assertEquals(401, response.statusCode());
        assertEquals("Unauthorized", response.body().asString());
    }

    @Test
    void testWithWithBasicAuth() {
        Response response = postmanAPIClient.getDataWithBasicAuth();
        assertEquals(200, response.statusCode());
        assertEquals(true, response.path("authenticated"));
    }

    @Test
    void testManageCookies() {
        Cookie myCookie = new Cookie.Builder("new_cookie", "new_value").build();
        Cookies cookies = new Cookies(myCookie);

        Response response = postmanAPIClient.getCookies(cookies);
        assertEquals("new_value", response.jsonPath().getString("cookies.new_cookie"));
    }
}
