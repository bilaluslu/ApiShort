package ApiTests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestsPOJODeserizalization {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    @Test
    public void test1 () {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        //response.prettyPrint();

        // GSON --> de-serialization
        // how to convert JSON response to our spartan class
        Spartan spartan1 = response.body().as(Spartan.class);
        System.out.println( spartan1.toString() );

        Assert.assertEquals( spartan1.getId() , 15 );
        Assert.assertEquals( spartan1.getName() , "Meta" );
        Assert.assertEquals( spartan1.getGender() , "Female" );
        Assert.assertEquals( spartan1.getPhone() , 1938695106 );

    }

    @Test
    public void gsonExample(){

        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        // using gson method do de-serialize our json method
        Spartan spartanMeta = gson.fromJson(myJsonBody,Spartan.class);

        System.out.println( spartanMeta.toString() );

        // serialization Java object --> Json body

        Spartan spartan = new Spartan(101,"Mike","Male",3211236541l);

        // converting custom class to Json ( serialization
        String jsonBody = gson.toJson( spartan );
        System.out.println( jsonBody );

    }
























}
