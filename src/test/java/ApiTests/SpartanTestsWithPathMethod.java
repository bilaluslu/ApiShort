package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanTestsWithPathMethod {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    /*
     * given accept type Json
     * and path param value is 10
     * when user sends GET request to api/spartans/{id}
     * then response status code should be 200
     * and response content type: application/json;charset=UTF-8
     * and  response payload values match the following: (response payload == response body)
        *  id is 10
        *  names is "Lorenza"
        *  gender is Female
        *  phone is 3312820936
     */

    @Test
    public void test1 () {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10).when()
                .get("/api/spartans/{id}");

        // verify status code
        Assert.assertEquals(response.statusCode() , 200 );

        // verify content type
        Assert.assertEquals( response.contentType() , "application/json;charset=UTF-8" );

        System.out.println("ID = " + response.path("id").toString());
        System.out.println("Name = " + response.body().path("name").toString());
        System.out.println("Gender = " + response.body().path("gender").toString());
        System.out.println("Phone = " + response.body().path("phone").toString());

        int ID = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("ID = " + ID);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Assert.assertEquals(ID ,10);
        Assert.assertEquals(name,"Lorenza");
        Assert.assertEquals(gender, "Female");
        Assert.assertEquals(phone, 3312820936l);

    }

    @Test
    public void test2 () {

        Response response = RestAssured.get("/api/spartans");

        // extract first id
        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        // extract first name
        String first1stName = response.path("name[3]");
        System.out.println("first1stName = " + first1stName);

        // extract last name
        String last1stName = response.path("name[-1]");
        System.out.println("last1stName = " + last1stName);

        // extract all first names and print them
        List<String> names = response.path("name");
        System.out.println("names = " + names);
        System.out.println("names sizes = " + names.size());

        // extract all phones and print them
        List<Object> phones = response.path("phone");
        System.out.println("phones = " + phones);


    }






















}
