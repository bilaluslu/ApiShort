package ApiTests;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTests {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }


    @Test
    public void viewSpartanTest1(){
        Response response = RestAssured.get("/api/spartans");

        // print status code
        System.out.println("statusCode = " + response.statusCode());

        // print body
        System.out.println("Printing body as STRING = " + response.body().asString());

        // printing body pretty way
        System.out.println("Printing body as PRETTY way = " + response.body().prettyPrint());

    }


    // when user send GET request to api/spartans end point
    // then status code must be 200
    //and body should contain ...
    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get("/api/spartans");

        // print status code
        System.out.println("statusCode = " + response.statusCode());
        // verify status code is 200
        Assert.assertEquals(response.statusCode() , 200);

        // check body contain Allen
        Assert.assertTrue(response.body().asString().contains("Allen"));
    }

    // given accept type is Json
    // when user sends a get request to spartanAllURL
    // then response status code is 200
    // and response body should be Json format
    @Test
    public void viewSpartanTest3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                            .when().get("/api/spartans");

        // verifying status code
        System.out.println("statusCode = " + response.statusCode());
        Assert.assertEquals(response.statusCode() , 200);

        // verify response body Json
        System.out.println("contentType = " + response.contentType());
        Assert.assertEquals( response.contentType() , "application/json;charset=UTF-8");
    }


























}
