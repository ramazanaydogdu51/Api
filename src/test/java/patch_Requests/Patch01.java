package patch_Requests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Patch01 extends JsonPlaceholder_BaseUrl {
    /*  sadece title i degisteceksek patch yapariz part part degistirmedir.
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "patch methodu part part update eder"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
                                            "userId": 10,
                                            "id": 198,
                                            "title": "patch methodu part part update eder",
                                            "completed": true
                                        }
     */

    @Test
    public void test01() {
        //1. Set the URL
        spec.pathParams("first","todos","second",198);

        //2  Set the Request Body
        JsonPlaceHolderTestData jsonPlaceHolderTestDataObj= new JsonPlaceHolderTestData();
       // Map<String,Object> expectedDataMap=     jsonPlaceHolderTestDataObj.expectedDataWithAllKeys(null,"patch methodu part part update eder",null);
        //yukariya null neden koyduk biz sadece "title"yi degisterecegiz digerlerine dokunmayagız bunun icin null girdik.Ama
        //null girersek null olarak gunceller bunu asmak icin methodo olusturalim diyelim ki null degilse guncelle null ise elleme.
        Map<String,Object> expectedDataMap=
        jsonPlaceHolderTestDataObj.expectedDataWithMissingKeys(null,"patch methodu part part update eder",null);
        System.out.println("expectedDataMap = " + expectedDataMap);
        //3. Send the PATCH Request and Get the Respons

        Response response   =      given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().patch("/{first}/{second}");
        response.prettyPrint();// contentType methodunu yazmazsak guncelleme yapmaz

        //4. Do Assertions
        response.then().assertThat().statusCode(200).body("title", Matchers.equalTo(expectedDataMap.get("title")));

        //When you do PATCH Assertion, just the data you updated should be asserted. But if someone insists on assert all parts do the following
        Map<String, Object> MapToAssertAllDetails =  jsonPlaceHolderTestDataObj.expectedDataWithAllKeys(10, "patch methodu part part update eder", true);
        response.then().assertThat().statusCode(200).body("title", equalTo(MapToAssertAllDetails.get("title")),
                "userId", equalTo(MapToAssertAllDetails.get("userId")),
                "completed", equalTo(MapToAssertAllDetails.get("completed")));

    }
}
