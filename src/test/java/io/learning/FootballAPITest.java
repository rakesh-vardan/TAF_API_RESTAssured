package io.learning;

import io.learning.client.FootballAPIClient;
import io.learning.client.IPIFyAPIClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FootballAPITest extends BaseTest {

    @Autowired
    private FootballAPIClient footballAPIClient;

    @Test
    void verifyTeamData() {
        Response response = footballAPIClient.getTeamSpecificData("66");
        assertEquals(200, response.statusCode());

        // approach-1
        assertEquals("Manchester United FC", response.path("name"));

        // approach-2
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Manchester United FC", jsonPath.get("name"));

        // approach-3
        String name = JsonPath.from(response.asString()).get("name");
        assertEquals("Manchester United FC", name);
    }

    @Test
    void verifyData() {
        Response response = footballAPIClient.getAllTeamsData();
        assertEquals("1. FC Köln", response.path("teams[0].name"));
        assertEquals("SV Wacker Burghausen", response.path("teams[-1].name"));

        List<String> namesList = response.path("teams.name");
        assertEquals(50, namesList.size());

        List<Map<String, ?>> data = response.path("teams");
        assertEquals(50, data.size());

        Map<String, ?> oneTeamData = response.path("teams.find { it.name == 'FC Augsburg'}");
        assertEquals("Augsburg", oneTeamData.get("shortName"));
        assertEquals("WWK Arena", oneTeamData.get("venue"));
    }
}
