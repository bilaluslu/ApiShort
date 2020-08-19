package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Practice {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    @Test
    public void practice1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 16)
                .when().get("api/spartans/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        Assert.assertEquals( response.statusCode() , 200 );
        Assert.assertEquals( response.contentType() , "application/json;charset=UTF-8" );

        response.prettyPrint();
    }

    @Test
    public void practice2(){
       /*
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Male")
                .and().queryParam("nameContains", "ea")
                .when().get("/api/spartans/search");

        response.prettyPrint();
*/

        // to store in a Map
        Map<String,Object> maps = new HashMap<>();
        maps.put("gender","Female");
        maps.put("nameContains" , "ea");

        // send request
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(maps)
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }

    @Test
    public void practice3(){

        Map<String,Object> storeInMap = new HashMap<>();
        storeInMap.put("gender","Male");
        storeInMap.put("nameContains","ea");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(storeInMap)
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }

    @Test
    public void practice4(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "ge")
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }

    @Test
    public void practice5(){
        Map<String,Object> fillUpMap = new HashMap<>();
        fillUpMap.put("gender","Male");
        fillUpMap.put("nameContains","ra");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(fillUpMap)
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }


















}
