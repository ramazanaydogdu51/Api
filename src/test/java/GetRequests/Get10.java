package GetRequests;

import BaseUrls.GoRest_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get10 extends GoRest_BaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users/2535
    When
        User send GET Request to the url
    Then
        Status Code should be 200
    And
        Response body should be like
            "meta": null,                                       Outer Map   From Response Body
            "data": {                                           Outer Map   From Response Body
                 "id": 2535,                                    Inner Map   From "data"
                 "name": "Ambar Namboothiri Jr.",               Inner Map   From "data"
                 "email": "jr_ambar_namboothiri@treutel.io",    Inner Map   From "data"
                 "gender": "male",                              Inner Map   From "data"
                 "status": "active"                             Inner Map   From "data"
                    }
     */

    @Test
    public void test01() {
        //1      Set the URL
        spec.pathParams("first","users","second",2535);

        //2      Set the Expected Data
        //"data" icin map olusturalim put yapalım
        Map<String,Object> dataKeyMap = new HashMap<>();
                  dataKeyMap.put("id",2535);
                  dataKeyMap.put("name","Ambar Namboothiri Jr.");
                  dataKeyMap.put("email","jr_ambar_namboothiri@treutel.io");
                  dataKeyMap.put("gender","male");
                  dataKeyMap.put("status","active");
        //"data" icin map olusturuldu ve ekleme yapildi
        System.out.println("expectedDataMap = " + dataKeyMap);
        //outer icin map olusturalim

        Map<String,Object> expectedBodyMap=new HashMap<>();
        expectedBodyMap.put("meta",null);
        expectedBodyMap.put("data",dataKeyMap);
        System.out.println("expectedBodyMap = " + expectedBodyMap);

        //3 Send the Request and Get the response
        Response response=
        given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();
        Map<String,Object>   actualBodyMap=
        response.as(HashMap.class);
        System.out.println("actualBodyMap = " + actualBodyMap);

        //4 Do Assertion
        SoftAssert softAssert= new SoftAssert();
        //softAssert.assertEquals(actualBodyMap,expectedBodyMap);
         // Assert.assertEquals(expectedBodyMap,actualBodyMap);
        //Toplu assert edince bir hatayi bulmak zor ondan dolayi ayri ayri assert edecegiz.
      softAssert.assertEquals(actualBodyMap.get("meta"),expectedBodyMap.get("meta"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("id"),dataKeyMap.get("id"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("name"),dataKeyMap.get("name"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("email"),dataKeyMap.get("email"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("gender"),dataKeyMap.get("gender"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("status"),dataKeyMap.get("status"));
                   softAssert.assertAll();
    }


    //bir kismini Method ile yapalim

    @Test
    public void test02() {
        //1      Set the URL
        spec.pathParams("first","users","second",2535);

        //2      Set the Expected Data
        GoRestTestData dataKeyMapObj = new GoRestTestData();

        Map<String,Object> dataKeyMap=dataKeyMapObj.dataKeyAllMap(2535,"Ambar Namboothiri Jr.","jr_ambar_namboothiri@treutel.io","male","active");
        System.out.println("dataKeyMap = " + dataKeyMap);

        Map<String,Object> expectedBodyMap=new HashMap<>();
        expectedBodyMap.put("meta",null);
        expectedBodyMap.put("data",dataKeyMap);
        System.out.println("expectedBodyMap = " + expectedBodyMap);

        //3 Send the Request and Get the response
        Response response=
                given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();
        Map<String,Object>   actualBodyMap=
                response.as(HashMap.class);
        System.out.println("actualBodyMap = " + actualBodyMap);
        //4 Do Assertion
        SoftAssert softAssert= new SoftAssert();
        //softAssert.assertEquals(actualBodyMap,expectedBodyMap);
        // Assert.assertEquals(expectedBodyMap,actualBodyMap);
        //Toplu assert edince bir hatayi bulmak zor ondan dolayi ayri ayri assert edecegiz.
        softAssert.assertEquals(actualBodyMap.get("meta"),expectedBodyMap.get("meta"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("id"),dataKeyMap.get("id"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("name"),dataKeyMap.get("name"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("email"),dataKeyMap.get("email"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("gender"),dataKeyMap.get("gender"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("status"),dataKeyMap.get("status"));
        softAssert.assertAll();
    }


    //*********************TAMAMINI METHOD İLE YAPALİM***************************
    @Test
    public void test03() {
        //1      Set the URL
        spec.pathParams("first","users","second",2535);

        //2      Set the Expected Data
        GoRestTestData dataKeyMapObj = new GoRestTestData();

        Map<String,Object> dataKeyMap=dataKeyMapObj.dataKeyAllMap(2535,"Ambar Namboothiri Jr.","jr_ambar_namboothiri@treutel.io","male","active");
        System.out.println("dataKeyMap = " + dataKeyMap);

        GoRestTestData expectedBodyMapObj = new GoRestTestData();
        Map<String,Object> expectedBodyMap=
        expectedBodyMapObj.expectedBodyMap(null,dataKeyMap);


        System.out.println("expectedBodyMap = " + expectedBodyMap);

        //3 Send the Request and Get the response
        Response response=
                given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();
        Map<String,Object>   actualBodyMap=
                response.as(HashMap.class);
        System.out.println("actualBodyMap = " + actualBodyMap);
        //4 Do Assertion
        SoftAssert softAssert= new SoftAssert();
        //softAssert.assertEquals(actualBodyMap,expectedBodyMap);
        // Assert.assertEquals(expectedBodyMap,actualBodyMap);
        //Toplu assert edince bir hatayi bulmak zor ondan dolayi ayri ayri assert edecegiz.
        softAssert.assertEquals(actualBodyMap.get("meta"),expectedBodyMap.get("meta"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("id"),dataKeyMap.get("id"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("name"),dataKeyMap.get("name"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("email"),dataKeyMap.get("email"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("gender"),dataKeyMap.get("gender"));
        softAssert.assertEquals(((Map) actualBodyMap.get("data")).get("status"),dataKeyMap.get("status"));
        softAssert.assertAll();
    }
}
