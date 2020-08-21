package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanPUTRequest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    @Test
    public void test1() {

        Map<String,Object> putRequest = new HashMap<>();
        putRequest.put("name","NamePUT");
        putRequest.put("gender","Female");
        putRequest.put("phone",1237894520L);

        RestAssured.given().contentType(ContentType.JSON)
                .and().pathParam("id", 11)
                .and().body(putRequest)
                .when().delete("/api/spartans/{id}");





    }






















}