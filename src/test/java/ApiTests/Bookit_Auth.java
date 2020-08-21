package ApiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bookit_Auth {

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://cybertek-reservation-api-qa3.herokuapp.com";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().header("Authorization", accessToken)
                .when().get("/api/campuses");


        Assert.assertEquals( response.statusCode() , 200 );

        response.prettyPrint();

    }



















}
