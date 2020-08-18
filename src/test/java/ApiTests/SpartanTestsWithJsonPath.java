package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestsWithJsonPath {

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
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);
        // how to read value with path() method
        int id = response.path("id");
        System.out.println("id = " + id);

        // how to read value with Json path
        JsonPath jsonPath = response.jsonPath();

        int id1 = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Assert.assertEquals(id,11);
        Assert.assertEquals(name,"Nona");
        Assert.assertEquals(gender,"Female");
        Assert.assertEquals(phone,7959094216l);

    }


























}
