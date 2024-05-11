package io.learning;

import io.learning.client.IPIFyAPIClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IpAddressTest extends BaseTest {

    @Autowired
    private IPIFyAPIClient ipifyApiClient;

    @Test
    void testGetIpAddress() {
        Response response = ipifyApiClient.getIpAddress();
        assertNotNull(response.jsonPath().getString("ip"));
    }
}
