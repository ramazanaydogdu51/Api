package GetRequests;


import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceholder_BaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json” //gonderilen dosyanin json formatinda olmasi gerek
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
     */

    @Test
    public void test01() {
        spec.pathParams("first","todos");
        Response response =         given().spec(spec)
                .accept(ContentType.JSON)
                //And Accept type is “application/json”
                // spec methodundan sonra accept methodu cikiyor ondan buraya girdik.
                // when'den sonra and oldugu icin when'in devami niteliginde oldugu icin bu method
                // kullanildi
                .when().get("/{first}");//When I send a GET request to the Url
            //Burasi bizim API a gonderdigimiz kisim gonderdigimizi aldik response icine attik.


       // response.prettyPrint();//bunlar body'nin icerisinde // bunlar gelen cevaplar
        //  Then
        //  HTTP Status Code should be 200
        //
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",hasSize(200)) // hasSize id su kadar mi diye yapilmis bir method
                //yanlis girilince kac tane oldugu cikiyor zaten
                .body("title",hasItem("quis eius est sint explicabo"))
                //tum titler'i alacak "quis eius est sint explicabo " var mi diye bakiyor.
                .body("userId",hasItems(2,7,9));// userId icinde 2 7 9 var mi dedik
    }
}
