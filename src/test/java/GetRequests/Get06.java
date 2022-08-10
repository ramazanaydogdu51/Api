package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class Get06 extends RestfulBooker_BaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/5
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
            {
                "firstname": "Mark",
                "lastname": "Jones",
                "totalprice": 863,
                "depositpaid": true,
                "bookingdates": { "checkin": "2017-02-26",
                                  "checkout":"2022-05-16" }
            }
     */

    @Test
    public void tes01() {
        //  Given
        //            https://restful-booker.herokuapp.com/booking/5
        spec.pathParams("first","booking","second",410);

        // When
        //            User send a GET request to the URL

        Response response =
        given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Then
        //            HTTP Status Code should be 200

        //And
        //            Response content type is “application/json”
        //And
        //            Response body should be like;
        //           {
        //    "firstname": "Jim",
        //    "lastname": "Brown",
        //    "totalprice": 111,
        //    "depositpaid": true,
        //    "bookingdates": {
        //        "checkin": "2018-01-01",
        //        "checkout": "2019-01-01"
        //    },
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Jim"),
                        "lastname",Matchers.equalTo("Brown"),
                        "totalprice",Matchers.equalTo(111),
                       "depositpaid",Matchers.equalTo(true),
                       "bookingdates.checkin",Matchers.equalTo("2018-01-01"), // nestedjson->  inner json icine girmek icin . koyduk cok basit
                        "bookingdates.checkout",Matchers.equalTo("2019-01-01"));



        //2.Way We will use JsonPath Class

        JsonPath json =response.jsonPath();  // response'ı jsonPath() method'u ile json e donusturduk.
        Assert.assertEquals("Jim",json.getString("firstname"));
        Assert.assertEquals("Brown",json.getString("lastname"));
        Assert.assertEquals(111,json.getInt("totalprice"));
        Assert.assertEquals(true,json.getBoolean("depositpaid"));
        Assert.assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        Assert.assertEquals("2019-01-01",json.getString("bookingdates.checkout"));

                                        //3.way soft assertion
        //To use soft assertion follow given 3 step
        //1 create softAssert object
        //2) by using softAssert object do assertion
        //3)Use assertAll method otherwise you will get pass everytim but it will not be meaningful

        //1)
        SoftAssert softAssert=new SoftAssert();
        //2) by using softAssert object do assertion
        softAssert.assertEquals(json.getString("firstname"),"Jim","Firstname did not match");// yanlis cikarsa bana bu mesaji ver
        softAssert.assertEquals(json.getString("lastname"),"Brown","lastname did not match");
        softAssert.assertEquals(json.getInt("totalprice"),111,"totalprice did not match");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"depositpaid did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01","chek did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01","check out did not match");

        softAssert.assertAll();
    }
}
