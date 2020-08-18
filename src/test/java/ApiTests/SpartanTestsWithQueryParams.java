package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestsWithQueryParams {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    /*
     * given accept type Json
     * and query parameters value is :
        * gender | Female
        * nameContains | J
     * when user sends GET request to api/spartans/search
     * then response status code should be 200
     * and response content type: application/json;charset=UTF-8
     * and "Female" should be in response payload (response payload == response body)
     * and "Janette" should be in response payload (response payload == response body)
     */
    @Test
    public void queryParam1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");

        // verify response status code
        Assert.assertEquals( response.statusCode() , 200 );

        // verify content type
        Assert.assertEquals( response.contentType() , "application/json;charset=UTF-8" );

        // verify Female
        Assert.assertTrue( response.body().asString().contains("Female") );

        // verify Male not exist
        Assert.assertFalse( response.body().asString().contains("Male") );

        // verify Janette
        Assert.assertTrue( response.body().asString().contains("Janette") );

        response.body().prettyPrint();
    }

    @Test
    public void queryParam2(){
        // creating map for query Param
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        // send request
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(paramsMap).when().get("/api/spartans/search");

        // verify response status code
        Assert.assertEquals( response.statusCode() , 200 );

        // verify content type
        Assert.assertEquals( response.contentType() , "application/json;charset=UTF-8" );

        // verify Female
        Assert.assertTrue( response.body().asString().contains("Female") );

        // verify Male not exist
        Assert.assertFalse( response.body().asString().contains("Male") );

        // verify Janette
        Assert.assertTrue( response.body().asString().contains("Janette") );

        response.body().prettyPrint();
    }































}
