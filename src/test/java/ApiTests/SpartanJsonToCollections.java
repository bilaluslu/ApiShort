package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SpartanJsonToCollections {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    /*
     * given accept type Json
     * and path parameter spartan ID is 11
     * when user sends GET request to api/spartans/{id}
     * then response status code should be 200
     * and response content type is JSON : application/json;charset=UTF-8
     * and "id": 11,
     *      "name": "Nona",
     *      "gender": "Female",
     *      "phone": 7959094216
     */

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .and().get("/api/spartans/{id}");

        // convert Json to Java Collections(Maps)
        Map<String,Object> spartanMap = response.body().as(Map.class);

        System.out.println( spartanMap.get("id") );
        System.out.println( spartanMap.get("name") );
        System.out.println( spartanMap.get("gender") );
        

        // example verification one side map / expected result
        Assert.assertEquals( spartanMap.get("name") , "Nona" );
    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint(); // prints all spartans

        // convert full Json body to list of Maps
        List<Map<String,Object>> listOfSpartans = response.body().as(List.class);

        // print all data of first spartan
        System.out.println( listOfSpartans.get(0) );

        // store in a Map
        Map<String,Object> firstSpartan = listOfSpartans.get(2);
        System.out.println( firstSpartan.get("name") );

        int counter = 1;
        for ( Map<String,Object> map : listOfSpartans ) {
            System.out.println( counter + " - spartan " + map );
            counter++;
        }

    }



























}
