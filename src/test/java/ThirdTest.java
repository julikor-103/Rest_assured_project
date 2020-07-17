import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import pojo.Location;

import static io.restassured.RestAssured.given;

public class ThirdTest {

    @Test //test pass
    public void expectBeverlyHills() {
        Location location = given().
        when().log().everything().
        get("http://api.zippopotam.us/us/90210").
        as(Location.class);

        Assert.assertEquals("Beverly Hills",
                location.getPlaces().get(0).getPlaceName());
}

@Test
public void checkStatusCode_expected200(){
    Location location = new Location();
    location.setCountry("Netherlands");
    
    given().
      contentType(ContentType.JSON).
            body(location).
            when().
            post("http://api.zippopotam.us/AS/96799").
            then().
            assertThat().
            statusCode(200);
    }
}

