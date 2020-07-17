import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(DataProviderRunner.class)

public class FirstRestTest {
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "Beverli Hils"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void checkPlaceNameInResponseBody(String countryCode, String zipCode, String expectedPlaceName) {
        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().get("http://api.zippopotam.us/{countryCode}/{zipCode}").
                then().
                assertThat().body("place[0].'place name'", equalTo(expectedPlaceName))
                .and().statusCode(200);
    }

    @Test
    public void checkPlaceNameInResponseBody() {
        given().
                when().
                get("http://zippotman.us/us/90210").
                then().assertThat().
                body("places[]0.'place name'", equalTo("Beverly Hills"))
                .and().statusCode(200);
    }
    @Test
    public void checkPlaceNameInResponseBodyExpectSchenectady() {
        given().
         when().get("http://zippotman.us/us/12345").
                then().
                assertThat().
                body("place[0]. 'place name'", equalTo("Schenectady"));
    }
    @Test
    public void expectWaverley(){
        given().
                when().get("http://zippotman.us/ca/B2R").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Waverley"));
    }
}
