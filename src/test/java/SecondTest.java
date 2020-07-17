
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(DataProviderRunner.class)
public class SecondTest {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build(); //    addHeader("bareer", "dfgyhjj"). 
    }



    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test

    public void expectBeverlyHills_withRequestSpec() {
        given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
public void checkPlaceNameInResponseBody_expectBeverlyHills_withResponseSpec(String countryCode, String zipCode, String expectedPlaceName){
        given().
                spec(requestSpec).
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when()
                .get("/{countryCode}/{zipCode}").
                then()
                .assertThat().body("place[0]. 'place name'", equalTo(expectedPlaceName)).
                and().
                spec(responseSpec);
    }

    @AfterClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }
}

