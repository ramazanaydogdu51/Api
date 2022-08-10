package put_requests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Put01 extends JsonPlaceholder_BaseUrl {
    /*
    { bu verileri asadagiler ile degistirecegiz id zaten standart ona dokunmucaz
                    "userId": 10,
                    "id": 198,
                    "title": "quis eius est sint explicabo",
                    "completed": true
}
     */

    //Task
    /*   Amac  olan verileri degistirecegiz,
          Given
                      https://jsonplaceholder.typicode.com/todos/198
                      {
                          "userId": 21,
                          "title": "bu mac bizim",
                          "completed": false
                      }
          When
                    I sende Put Request the URL
          Then
                    Status code is 200
          And
                    Response body should be like this {
                                                          "userId": 21,
                                                          "title": "bu mac bizim",
                                                          "completed": false
                                                      }
     */

    @Test
    public void test01() {
        //1   Set the URL
        spec.pathParams("first","todos","second",198);

        //2    Set the Expected Data
            //methodları cagiralim zaten vardi
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();

        Map<String,Object> expectedDataMap=        jsonPlaceHolderTestData.expectedDataWithAllKeys(51,"bu mac bizim",false);

        //3     Set the PUT Request
        Response response=
        given().spec(spec).when().contentType(ContentType.JSON).body(expectedDataMap).put("/{first}/{second}");
        response.prettyPrint();


        //4     Do assertions
        Map<String,Object>actualDataMap=        response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        response.then().assertThat().statusCode(200);


    }
}
