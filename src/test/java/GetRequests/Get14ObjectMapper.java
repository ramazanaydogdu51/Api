package GetRequests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get14ObjectMapper extends JsonPlaceholder_BaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */


    @Test
    public void test01() {
        //1     Set the URL
        spec.pathParams("first","todos","second",198);


        //2     Set the Expected Data
        String expectedData="{\n" +
                " \"userId\": 10,\n" +
                " \"id\": 198,\n" +
                " \"title\": \"quis eius est sint explicabo\",\n" +
                " \"completed\": true\n" +
                "}";
        // bu json formatindadir bizim yazdigimiz method bunu istedigimiz data turune cevirir.
        // Biz bunu map'e cevirelim cunku "Key" and "Value" oldugu icin.

                //Bunu her zaman kullanacaksak data'ya gidip daha duzenli daha dinamik olsun diye obje olusturup da yapariz.
        //II. yol
        JsonPlaceHolderTestData jsonPlaceObj= new JsonPlaceHolderTestData();
        Map<String, Object>expectedData2Map=        jsonPlaceObj.expectedDataWithMissingKeys(10,"quis eius est sint explicabo",true);
        //III. yol
        String expectedData3String=        jsonPlaceObj.expectedDataInString(10,"quis eius est sint explicabo",true);

        Map<String,Object> expectedDataMap  =        JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        //3 Send the Get Request and get the Response
        Response response=        given().spec(spec).when().get("/{first}/{second}");
        //expected data bizim map bundan dolayı response variable'nı da map yapalim.

        Map<String,Object> actualDataMap =         JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        //json variable'ı bizden String istiyor
        //Bundan dolayi biz de response'ı String'e cevirdik bunu da method ile yaptik.

        //4.Do Assertions
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(actualDataMap.get("userId"),expectedDataMap.get("userId"));
        Assert.assertEquals(actualDataMap.get("title"),expectedDataMap.get("title"));
        Assert.assertEquals(actualDataMap.get("completed"),expectedDataMap.get("completed"));
        Assert.assertEquals(actualDataMap.get("id"),expectedDataMap.get("id"));

        Assert.assertEquals(actualDataMap.get("userId"),expectedData2Map.get("userId"));
        Assert.assertEquals(actualDataMap.get("title"),expectedData2Map.get("title"));
        Assert.assertEquals(actualDataMap.get("completed"),expectedData2Map.get("completed"));
    }

    @Test //This is the best !!!!!!!!!!!!!!!!!!!!!!
    public void test02Pojo() {
        //1     Set the URL
        spec.pathParams("first","todos","second",198);

        //2     Set the Expected Data
        JsonPlaceHolderTestData jsonPlaceObj= new JsonPlaceHolderTestData();
        String expectedDataString=        jsonPlaceObj.expectedDataInString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedDataPojo=
        JsonUtil.convertJsonToJavaObject(expectedDataString, JsonPlaceHolderPojo.class);
        System.out.println("expectedDataPojo = " + expectedDataPojo);

        //3 Send the Get Request and get the Response
        Response response   =        given().spec(spec).when().get("/{first}/{second}");

        //4 Do Assertions
        JsonPlaceHolderPojo actualDataPojo =    JsonUtil.convertJsonToJavaObject(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualDataPojo = " + actualDataPojo);
        Assert.assertEquals(actualDataPojo.getUserId(),expectedDataPojo.getUserId());
        Assert.assertEquals(actualDataPojo.getCompleted(),expectedDataPojo.getCompleted());
        Assert.assertEquals(actualDataPojo.getTitle(),expectedDataPojo.getTitle());

    }
}
