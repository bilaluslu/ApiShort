package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestsWithHamcrest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://34.224.4.31:8000";
    }

    /*
     * given accept type Json
     * and path parameter spartan ID is 15
     * when user sends GET request to api/spartans/{id}
     * then response status code should be 200
     * and response content type is JSON : application/json;charset=UTF-8
     * and "id": 15,
     *      "name": "Meta",
     *      "gender": "Female",
     *      "phone": 1938695106
     */

    @Test
    public void test1(){

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")   // until this line request
                .then().statusCode(200).and()           // after this line response
                .assertThat().contentType("application/json;charset=UTF-8");
    }

    @Test
    public void test2(){

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id", Matchers.equalTo(15)
                                        ,"name",Matchers.equalTo("Meta")
                                        ,"gender",Matchers.equalTo("Female")
                                        ,"phone",Matchers.equalTo(1938695106))  ;

    }

    @Test
    public void practice1 () {

        RestAssured.given().accept(ContentType.JSON).pathParam("id",75)
                .and().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id",Matchers.equalTo(75)
                                            ,"name",Matchers.equalTo("Leland")
                                            ,"gender",Matchers.equalTo("Female")
                                            ,"phone",Matchers.equalTo(9672110769l) ) ;
    }

    @Test
    public void practice2 () {
        RestAssured.given().accept(ContentType.JSON).pathParam("id",141)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(404)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("status",Matchers.equalTo(404)
                                            ,"error",Matchers.equalTo("Not Found") );
    }































}
