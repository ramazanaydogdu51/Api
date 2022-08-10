package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;

import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
public class Get12Pojo extends RestfulBooker_BaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2266
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Michael",
                                    "lastname": "Robinson",
                                    "totalprice": 136,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2022-11-13",
                                        "checkout": "2022-12-26"
                                    },
                                     "additionalneeds": "lunch"
                                }
     */
    @Test
    public void get01Pojo(){

        //1.Step:Set the URL
        spec.pathParams("first", "booking", "second", 2266);

        //2.Step: Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-11-13", "2022-12-26");
        BookingPojo bookingPojo = new BookingPojo("Michael", "Robinson", 136, true, bookingDatesPojo, "lunch");

        //3.Step: Send the GET Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        System.out.println("**************************************");
        System.out.println("bookingPojo = " + bookingPojo);
        //4.Step: Do Assertions
        BookingPojo actualPojo = response.as(BookingPojo.class);

        assertEquals(bookingPojo.getFirstname(), actualPojo.getFirstname());
        assertEquals(bookingPojo.getLastname(), actualPojo.getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getDepositpaid());
        //1.Way:
        //assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBookingdates().getCheckin());
        //assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBookingdates().getCheckout());
        //2.Way:Recommended
        assertEquals(bookingDatesPojo.getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualPojo.getBookingdates().getCheckout());

    }

}
