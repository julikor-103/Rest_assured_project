import groovy.json.JsonException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PetTest {
    private static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setUpPetTest() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://petstore.swagger.io/v2")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void addNewPetInStore() throws JsonException, InterruptedException {
        given().
                spec(requestSpec).
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"id\": 456789651,\n" +
                        "    \"category\": {\n" +
                        "        \"id\": 56788999761,\n" +
                        "        \"name\": \"julikor\"\n" +
                        "    },\n" +
                        "    \"name\": \"julikor\",\n" +
                        "    \"photoUrls\": [\n" +
                        "        \"julikor\"\n" +
                        "    ],\n" +
                        "    \"tags\": [\n" +
                        "        {\n" +
                        "            \"id\": 6765443351,\n" +
                        "            \"name\": \"julikor\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"status\": \"available\"\n" +
                        "}").
                when().
                post("https://petstore.swagger.io/v2/pet").
                then().
                //spec(responseSpec).
                assertThat().
                statusCode(200);
    }

    @Test
    public void findByStatus_available() {
        given().
                spec(requestSpec).
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"id\": 456789651,\n" +
                        "    \"category\": {\n" +
                        "        \"id\": 56788999761,\n" +
                        "        \"name\": \"julikor\"\n" +
                        "    },\n" +
                        "    \"name\": \"julikor\",\n" +
                        "    \"photoUrls\": [\n" +
                        "        \"julikor\"\n" +
                        "    ],\n" +
                        "    \"tags\": [\n" +
                        "        {\n" +
                        "            \"id\": 6765443351,\n" +
                        "            \"name\": \"julikor\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"status\": \"available\"\n" +
                        "}").
                when().
                get("https://petstore.swagger.io/v2/pet/findByStatus?status=available").
                then().
                assertThat().statusLine("HTTP/1.1 200 OK");
    }

    @AfterClass
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }
}




