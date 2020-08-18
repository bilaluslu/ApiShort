package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestsWithPathParameters {


    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    /*
     * given accept type Json
     * and ID parameter value is 18
     * when user sends GET request to api/spartans/{id}
     * then response status code should be 200
     * and response content type: application/json;charset=UTF-8
     * amd "Allen" should be in response payload (response payload == response body)
     */
    @Test
    public void pathTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 18).when().get("/api/spartans/{id}");

        // verify status code
        Assert.assertEquals(response.statusCode() , 200 );

        // verify content type
        Assert.assertEquals( response.contentType() , "application/json;charset=UTF-8" );

        // verify "Allen" exists
        Assert.assertTrue( response.body().asString().contains("Allen"));
    }

    /*
     * given accept type Json
     * and ID parameter value is 423
     * when user sends GET request to api/spartans/{id}
     * then response status code should be 404
     * and response content type: application/json;charset=UTF-8
     * amd "Spartan Not Found" should be in response payload (response payload == response body)
     */

    @Test
    public void negativePathParamTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 423).when().get("/api/spartans/{id}");

        // verify status code 404
        Assert.assertEquals(response.statusCode(),404);

        // verify content type
        Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");

        // verify "Spartan Not Found" exists
        Assert.assertTrue(response.body().asString().contains("Spartan Not Found"));

        response.body().prettyPrint();

    }
























}
