package post_requests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Post02 extends JsonPlaceholder_BaseUrl {
    /*Amac burada post olusturmak olusturdugumuzu da assert etmek bu
        Given
             1)      https://jsonplaceholder.typicode.com/todos
             2)         {
                             "userId": 673,
                             "id": 55,
                             "title": "life is good",
                             "completed": true
                        }
        When
             I send ****POST**** Request to the URL
        Then
            Status Code is 201
        And
            response body is like {    "userId": 1,
                                      "id": 1,
                                      "title": "delectus aut autem",
                                      "completed": false
                                    }
     */

    @Test
    public void test01() {
        //1   Set the Url
            spec.pathParam("first","todos");

        //2     Set the Expected Data(Request Body , Payload)
            //methodlarimizi getirelim class'dan
            JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
            Map<String,Object> expectedDataMap=
                jsonPlaceHolderTestData.expectedDataWithAllKeys(673,"life is gooc",true);

        //3     Send POST Request and Get the Response
        Response response=
            given().spec(spec).when().contentType(ContentType.JSON).body(expectedDataMap).post("/{first}");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);

        //4    Do Assertion
        Map<String,Object> actualDataMap=
        response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("userid"),actualDataMap.get("userid"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));

    }
}
