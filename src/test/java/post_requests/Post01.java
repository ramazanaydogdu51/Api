package post_requests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.RestfullBookerTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Post01 extends RestfulBooker_BaseUrl {
    /* Amac burada post olusturmak olusturdugumuzu da assert etmek bu
        Given
            1)https://restful-booker.herokuapp.com/booking
            2){
                "firstname": "Brandon",
                 "lastname": "Anderson",
                 "totalprice": 192,
                 "depositpaid": false,
                 "bookingdates": {
                     "checkin": "2013-02-23",
                     "checkout": "2022-12-31"
    }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {  "bookingid": 4321,
                                                "booking": {
                                                "firstname": "Brandon",
                                                "lastname": "Anderson",
                                                "totalprice": 192,
                                                "depositpaid": false,
                                                "bookingdates": {
                                                    "checkin": "2013-02-23",
                                                    "checkout": "2022-12-31"
                                                        }
    }
        Note1: The data you send in the request is called "Request Body" or " Payload"
        Eger bir "Post" olusturmak istiyorsanız 2 şeye ihtiyacınız vardir 1 URL , 2 "Payload" mean"Request Body"

        Note2:The data you get in response is called "Response Body"
     */

    @Test
    public void test01() {
        //1 Set the URL
        spec.pathParams("first","booking");

        //2 Set the Expected Data
        RestfullBookerTestData herOkuApp= new RestfullBookerTestData();
       Map<String,String>bookingDatesMap= herOkuApp.bookingDatesSetUp("2013-02-23","2022-12-31");

        Map<String, Object> expectedDataMap=
                herOkuApp.expectedDataSetUp("Brandon","Aydogdu",192,false,bookingDatesMap);

        //3. Step: Send ***POST*** Request and Get the Response

        Response response =
        given().spec(spec).when().contentType(ContentType.JSON).body(expectedDataMap).post("/{first}");
            //Post icin 2 tane  gereksinim var demiştik. 1 URL, 2 "Response Body"
            //2 gereksinimi de yazdik simdi anlayacagi dile cevirelim yani Contenttype.json
        response.prettyPrint();
        response.then().assertThat().statusCode(200);


        //4 Do assertion
        Map<String,Object> actualDataMap= response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("firstname"), ((Map) actualDataMap.get("booking")).get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), ((Map) actualDataMap.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));
        Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkin"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));



    }
}
