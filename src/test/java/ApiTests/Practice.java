package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
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
                .when().get("/api/spartans/{id}");

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

    @Test
    public void practice6(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",10)
                .when().get("/api/spartans/{id}");

        System.out.println("printing = " + response.body().path("name").toString() );
    }

    @Test
    public void practice7(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<String> allNames = response.path("name");
        System.out.println( allNames );
    }

    @Test
    public void practice8(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        System.out.println( id );
    }

    @Test
    public void practice9(){
        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 23)
                .log().all()
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("gender", Matchers.equalTo("Male"))
                .and().assertThat().body("id", Matchers.equalTo(23))
                .and().assertThat().body("name" , Matchers.equalTo("Bilal Updated"));


    }


















}
