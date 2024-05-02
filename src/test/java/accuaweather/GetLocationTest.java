package accuaweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import accuaweaher.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    void getLocation_search_returnMoscow() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Moscow")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(24,response.size());
        Assertions.assertEquals("Moscow", response.get(0).getEnglishName());
    }
}
