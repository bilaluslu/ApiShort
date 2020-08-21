package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanDELETERequest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    @Test
    public void test1() {

        RestAssured.given().contentType(ContentType.JSON)
                .and().pathParam("id", 101)
                .when().delete("/api/spartans/{id}");





    }




}
