package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get05_tekrar extends RestfulBooker_BaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"   with query
     */


    @Test
    public void test01() {
        spec.pathParams("first","booking").queryParams("firstname","Shameem","lastname","Brown");
        //Given
        //            https://restful-booker.herokuapp.com/booking
        Response response =
        given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        // body 29 iceriyor mu dene
        boolean is29= response.asString().contains("29");
        Assert.assertTrue(is29);

    }
}
