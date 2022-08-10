package GetRequests;

import BaseUrls.JsonPlaceholder_BaseUrl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;


import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.*;

public class Get08 extends JsonPlaceholder_BaseUrl {
    /* **************BİLGİ********************
    Serialization: To convert Java Object to JSON Data
   De-Serialization: To convert Json Data to Java Object

    To do De-Serialization and Serialization we can use th followings:
    1)Gson: Google Created
    2)Object Mapper: More popular
     */

    /*
    Given https://jsonplaceholder.typicode.com/todos/2

    When
        I send GET Request to the URL

    Then
        Status code is 200
        And "completed" is false
        And "userId" is 1
        And "title" is "quis ut nam facilis et officia qui"
        And header " Via" is "1.1" Vegur"
        And header "Server" is "cloudflare"

       {    **********************burasi expected data******************
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
        }
     */
    @Test
    public void test01() {
        //1.Step the url
        spec.pathParams("first","todos","second","2");

        //2.Step: Set the Expected Data
                        //expected data json data ama suan test method java'dayiz java json dilini bilmiyor
                      //Json'ı java object'ine çevirmem lazım
                      //objet kullandik cunku object hepsinin parent'i neden hepsi cunku yukarıda string int boolean var
                      //Map<String,Integer> yazarsak digerlerini kullanamayiz ondan Object yazdik
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        //yukaridaki kodlar beklenen degerler map seklindedir

        //3.Step: Send the request and Get the Response
        //asagidaki kodlar actual degerlerdir bunlar bize response verecek
        // o zaman response biz map'e cevirelim      Map<String,Object>  actualData=response.as(HashMap.class);
        Response response=
        given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object>  actualData=response.as(HashMap.class); //body'nin icindekileri actualData clasina atiyor bakalim yazdiralim
        System.out.println("actualData = " + actualData);
        //response'nin icindekileri as methodu ile HashMap'e ceviriyoruz artik response'un icindekiler Map oldu actualData ismi ile.

        //simdi expected data ve actual data ikisi de map oldu o zaman onları compare (karsilastirma) yapmamız gerek.

        //4) do assertions
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("StatusCode"),response.statusCode());      //headers oldugu icin
        Assert.assertEquals(expectedData.get("Via"),response.getHeader("Via"));      //headers oldugu icin
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));//headers oldugu icin
    }

    //bunu method ile yapalim bakalim diyelim ki her zaman kullanacagiz bunu bir sirkette .

    @Test
    public void test02() {

        //1     Set the url
        spec.pathParams("first","todos","second","2");

        //2     Set the expected data
        JsonPlaceHolderTestData expectedData=new JsonPlaceHolderTestData();//obje olusturuldu response body icin
        Map<String,Object> expectedDataMap=
        expectedData.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui",false);//
        System.out.println("expectedData = " + expectedData);//burdan obje yazdirir
        System.out.println("expectedData = " + expectedDataMap);//burdan map yazdirir
        expectedDataMap.put("id",2);
        expectedDataMap.put("StatusCode",200);
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");
        //3     Send the Request and Get the Response
        Response response=
                given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object>  actualData=response.as(HashMap.class); //body'nin icindekileri actualData clasina atiyor bakalim yazdiralim
        System.out.println("actualData = " + actualData);
        //response'nin icindekileri as methodu ile HashMap'e ceviriyoruz artik response'un icindekiler Map oldu actualData ismi ile.

        //simdi expected data ve actual data ikisi de map oldu o zaman onları compare (karsilastirma) yapmamız gerek.

        //4     Do assertions
        Assert.assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedDataMap.get("StatusCode"),response.statusCode());      //headers oldugu icin
        Assert.assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));      //headers oldugu icin
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));//headers oldugu icin

    }
}
