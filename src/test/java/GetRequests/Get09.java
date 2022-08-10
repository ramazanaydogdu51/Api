package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get09 extends RestfulBooker_BaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/11
        When
            I sende Get Request to the url
        Then
            Response body should be like that;
            {
                 "firstname": "Cezary",
                  "lastname": "Balis",
                  "totalprice": 9999,
                  "depositpaid": false,
                  "bookingdates": {
                      "checkin": "2022-07-09",
                      "checkout": "2022-08-11"
                  },
    "additionalneeds": "Breakfast"

     */
        /* ****************ACİKLAMA*****************
        {
                 "firstname": "Cezary",    =>OUT
                 "lastname": "Balis",    =>OUT
                 "totalprice": 9999,    =>OUT
                 "depositpaid": false,    =>OUT
                 "bookingdates": {    =>OUT      bunun icin ayrı map olusturcaz
                     "checkin": "2022-07-09",   =>INNER    String,String
                     "checkout": "2022-08-11"   =>INNER    String,String
                 },
                     "additionalneeds": "Breakfast"
         */
    @Test
    public void test01() {
        //1     Set the url
        spec.pathParams("first","booking","second",11);

        //2     Set the Expected Data

        //bookingdates(inner) icin map olusturalim
        Map<String,String> bookingDates= new HashMap<>();//key String, value String
        bookingDates.put("checkin","2022-07-09");
        bookingDates.put("checkout","2022-08-11");
        //bookingdates icin bookingDates map'i olusturuldu

        //simdi outter map olusturacagiz

        Map<String,Object> expectedData= new HashMap<>();
        //key'ler String ama value'ler farkli oldugu icin parent olan Object olusturduk
        expectedData.put("firstname","Cezary");
        expectedData.put("lastname","Balis");
        expectedData.put("totalprice",9999);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingDates);//map verecek bize
        expectedData.put("additionalneeds","Breakfast");
        System.out.println("expectedData = " + expectedData);

        //3     Send the Request and Get the Response
        Response response=
        given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object>actualDataMap=        response.as(HashMap.class);//response'a gelen cevaplari map'e cevirdik
        System.out.println("actualDataMap = " + actualDataMap);

        System.out.println("actualDataMap.get(\"bookingdates.checkin\") = " + ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        System.out.println("actualDataMap.get(\"bookingdates.checkin\") = " + ( ((Map<?, ?>) actualDataMap.get("bookingdates")).get("checkin")));
        System.out.println("(actualDataMap.get(\"bookingdates\")).get(\"checkout\") = " + ((Map<?, ?>) actualDataMap.get("bookingdates")).get("checkout"));
        //4     Do Assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualDataMap,expectedData);// toplu assert yapabiliriz
        softAssert.assertEquals(actualDataMap.get("firstname"),expectedData.get("firstname"),"firstname eslesmedi");
        softAssert.assertEquals(actualDataMap.get("lastname"),expectedData.get("lastname"),"lastname eslesmedi");
        softAssert.assertEquals(actualDataMap.get("totalprice"),expectedData.get("totalprice"),"totalprice eslesmedi");
        softAssert.assertEquals(actualDataMap.get("depositpaid"),expectedData.get("depositpaid"),"depositpaid eslesmedi");
        softAssert.assertEquals(actualDataMap.get("bookingdates"),expectedData.get("bookingdates"),"bookingdates eslesmedi");//tamamı ya da ayrı ayrı
        softAssert.assertEquals(((Map) actualDataMap.get("bookingdates")).get("checkin"), ( bookingDates.get("checkin")));
        softAssert.assertEquals(((Map<?, ?>) actualDataMap.get("bookingdates")).get("checkout"),bookingDates.get("checkout"));
        //**************************ONEMLI ACIKLAMA**********************
        //Map<String,Object>actualDataMap=        response.as(HashMap.class);   value burada object yapilmisti oraya ulasmak icin onu tekrar map yapalım
        //  bundan dolayi intellij teşekkür edelim kendisi casting yapmak icin getiriyor biz sadece map'te inner'a ulasmak icin get().get() diyoruz bunu idea kendisi map'e casting convert ediyor
        softAssert.assertEquals(actualDataMap.get("additionalneeds"),expectedData.get("additionalneeds"),"additionalneeds eslesmedi");
        softAssert.assertEquals(actualDataMap.get("expectedData"),expectedData.get("expectedData"),"expectedData eslesmedi");
        softAssert.assertAll();

        // Assert.assertEquals(expectedData.get("bookingdates"),actualDataMap.get("bookingdates"));
        // System.out.println("expectedData.get(\"bookingdates\") = " + expectedData.get("bookingdates"));
        // System.out.println("actualDataMap.get(\"bookingdates\") = " + actualDataMap.get("bookingdates"));
        // System.out.println("expectedData.get(\"firstname\") = " + expectedData.get("firstname"));
    }
}
