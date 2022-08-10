package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get05 extends RestfulBooker_BaseUrl {

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
        //https://restful-booker.herokuapp.com/booking?firstname=Henry&lastname=Moulin    //postmanden geldi
        spec.pathParams("first","booking")  // neden birinci adimda bunu yaptik cunku Set The Url dedigi icin
                .queryParams("firstname","Henry",
                        "lastname","Moulin");


        Response response =        given()//Given  ->    https://restful-booker.herokuapp.com/booking
                                    .spec(spec)
                                     .when().get("/{first}");//When  ->     User send a request to the URL

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        boolean is144 = response.asString().contains("144");//responce'um iceriyor mu 144
        Assert.assertTrue(is144);
    }
}
