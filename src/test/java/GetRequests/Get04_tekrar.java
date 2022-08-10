package GetRequests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04_tekrar extends JsonPlaceholder_BaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”   //gonderilen dosyanin json formatinda olmasi gerek
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     *///task

    @Test
    public void test01() {
        //Given
        //            https://jsonplaceholder.typicode.com/todos

        spec.pathParams("first","todos");//spec degiskenine ekleme yaptik
        // When
        //	 	    I send a GET request to the Url
        Response response =         given()
                .spec(spec)
                .accept(ContentType.JSON)
                .when()
                .get("/{first}");
        //Then
        //	        HTTP Status Code should be 200
        //And
        //	        Response format should be "application/json"        (contentType)
        //	    And
        //	        There should be 200 todos
        //	    And
        //	        "quis eius est sint explicabo" should be one of the todos title
        //	    And
        //	        2, 7, and 9 should be among the userIds
        response.then().assertThat()//dogrulamak icin assert sart
                .statusCode(200)//HTTP Status Code should be 200
                .contentType(ContentType.JSON) //Response format should be "application/json"        (contentType)
                .body("id",hasSize(200)) //There should be 200 todos
                .body("title", hasItem("quis eius est sint explicabo")) // "quis eius est sint explicabo" should be one of the todos title
                .body("userId",hasItems(2,7,9));// 2, 7, and 9 should be among the userIds




    }
}
