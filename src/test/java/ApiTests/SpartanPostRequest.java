package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanPostRequest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }
/*
     given accept type and Content-Type is Json
     and request Json body is:
          {
          "gender": "Female",
        "name": "Hale",
        "phone": 1234567890
          }
     When user sends POST request to "/spartans/"
     Then status code should be "201"
     and content type should be "application/json"
     and json payload/response should contain:
     "A Spartan is Born!" message and same data what is posted
*/
    @Test
    public void postWithString1 () {
        // long way without storing in a String
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "          \"gender\": \"Female\",\n" +
                        "        \"name\": \"Hale\",\n" +
                        "        \"phone\": 1234567890\n" +
                        "          }")
                .when().post("/api/spartans");

        response.prettyPrint();

        // verify status code is 201
        Assert.assertEquals( response.statusCode() , 201 );

        // verify content type is "application/json"
        Assert.assertEquals( response.contentType() , "application/json" );

        // verify success message "A Spartan is Born!"
        Assert.assertEquals( response.path("success") , "A Spartan is Born!");

        // verify request body
        JsonPath json = response.jsonPath();

        Assert.assertEquals( json.getString("data.name") , "Hale" );
        Assert.assertEquals( json.getString("data.gender") , "Female" );
        Assert.assertEquals( json.getLong("data.phone") , 1234567890L );
    }

    @Test
    public void postWithString2 () {
        // shorter way with storing in a String

        String bodyInfo = "{\n" +
                "          \"gender\": \"Female\",\n" +
                "        \"name\": \"Hale\",\n" +
                "        \"phone\": 1234567890\n" +
                "          }";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body( bodyInfo )
                .when().post("/api/spartans");

        response.prettyPrint();

        // verify status code is 201
        Assert.assertEquals( response.statusCode() , 201 );

        // verify content type is "application/json"
        Assert.assertEquals( response.contentType() , "application/json" );

        // verify success message "A Spartan is Born!"
        Assert.assertEquals( response.path("success") , "A Spartan is Born!");

        // verify request body
        JsonPath json = response.jsonPath();

        Assert.assertEquals( json.getString("data.name") , "Hale" );
        Assert.assertEquals( json.getString("data.gender") , "Female" );
        Assert.assertEquals( json.getLong("data.phone") , 1234567890L );
    }

    @Test
    public void postWithString3 () {
        // the shortest way with storing in a Map

        Map<String,Object> storeInMap = new HashMap<>();
            storeInMap.put("name","HaleMap");
            storeInMap.put("gender","Female");
            storeInMap.put("phone",1234567890L );

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body( storeInMap )
                .when().post("/api/spartans");

        response.prettyPrint();

        // verify status code is 201
        Assert.assertEquals( response.statusCode() , 201 );

        // verify content type is "application/json"
        Assert.assertEquals( response.contentType() , "application/json" );

        // verify success message "A Spartan is Born!"
        Assert.assertEquals( response.path("success") , "A Spartan is Born!");

        // verify request body
        JsonPath json = response.jsonPath();

        Assert.assertEquals( json.getString("data.name") , "HaleMap" );
        Assert.assertEquals( json.getString("data.gender") , "Female" );
        Assert.assertEquals( json.getLong("data.phone") , 1234567890L );
    }


    @Test
    public void PostWithPOJO () {
        // create Spartan object and used as a body for post request

        Spartan spartan = new Spartan();

        spartan.setName("BilalPOJO");
        spartan.setGender("Male");
        spartan.setPhone(1254745869L);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans");

        response.prettyPrint();

        // verify status code is 201
        Assert.assertEquals( response.statusCode() , 201 );

        // verify content type is "application/json"
        Assert.assertEquals( response.contentType() , "application/json" );

        // verify success message "A Spartan is Born!"
        Assert.assertEquals( response.path("success") , "A Spartan is Born!");

/*
        //=================== get request ==========================//

        Response response2 = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",200)
                .when().get("/api/spartans/{id}");

        Spartan spartanResponse = response2.body().as(Spartan.class);

        System.out.println( spartanResponse.toString() );
 */


    }


























}
