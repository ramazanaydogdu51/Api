package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;

public class Get15ObjectMapper extends RestfulBooker_BaseUrl {
     /*
        Given
	            https://restful-booker.herokuapp.com/booking/1878
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                        {
                            "firstname": "James",
                            "lastname": "Brown",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                                 "checkin": "2018-01-01",
                                                 "checkout": "2019-01-01"
                                             },
                            "additionalneeds": "Breakfast"
                        }
     */

    @Test
    public void tes01() {
        //1     Set the URL
        spec.pathParams("first","booking","second",1878);


        //2     Set the Expected Data
        String expectedData ="{\n" +
                "\"firstname\": \"James\",\n" +
                "\"lastname\": \"Brown\",\n" +
                "\"totalprice\": 111,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2018-01-01\",\n" +
                "\"checkout\": \"2019-01-01\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";
        BookingPojo expectedDataPojo =        JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3     Send Get Request and get the Response
        Response response=
        given().spec(spec).when().get("/{first}/{second}");


        //4. Do Assertions
        BookingPojo actualDataPojo=
        JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        //This is hard assertions
        Assert.assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        Assert.assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        Assert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        Assert.assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        Assert.assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        Assert.assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        Assert.assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());


        //Once SoftAssert class'ından obje olusturulur
        SoftAssert softAssert = new SoftAssert();
        // buraya assert methodlari uygulanir.


        //Sakin unutma
        softAssert.assertAll();
    }

    //Note Improve String expected Data to a Method in HerOkuAppTestData Class

}
