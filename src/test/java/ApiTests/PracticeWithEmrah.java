package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PracticeWithEmrah {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        int id = response.statusCode();
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals( 200 , response.statusCode() );

        Assert.assertEquals( "application/json;charset=UTF-8" , response.contentType() );
    }

    @Test
    public void test2(){

        // path parameter
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 25)
                .when().get("/api/spartans/{id}");

        System.out.println( "statusCode = " + response.statusCode() );
        System.out.println( "content type = " + response.contentType() );

        String id = response.path("id").toString();
        String name = response.path("name").toString();
        String gender = response.path("gender").toString();
        String phone = response.path("phone").toString();

        System.out.println( "id = " + id);
        System.out.println( "name = " + name);
        System.out.println( "gender = " + gender);
        System.out.println( "phone = " + phone);



    }













}