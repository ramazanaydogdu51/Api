package post_requests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class Post04Pojo extends JsonPlaceholder_BaseUrl {
 /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void test01() {
        //1  Set the URL
        spec.pathParam("first", "todos");

        //2 Set the expected data
        JsonPlaceHolderPojo expectedDataObject =new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //3 Send POST Request and get the Response
        Response response=// actual datalar response'da expectedData'lar  JsonPlaceHolderPojo class'ından gelen obje'de
        given().spec(spec).when().contentType(ContentType.JSON).body(expectedDataObject).when().post("/{first}");
        response.prettyPrint();
        //4 Do assertion
                // actual datalar response'da expectedData'lar  JsonPlaceHolderPojo class'ından gelen obje'de
                //assert etmek icin once data turlerini ayni yapalim.
                //response'ı  JsonPlaceHolderPojo'ına class'ına benzetelim.

       // response.as(HashMap.class); Map'e' benzetmek icin HashMap class'ın'dan .class yapıyorduk simdi aynisini
        // JsonPlaceHolderPojo clası icin yapalim
        JsonPlaceHolderPojo actualDataObject =
        response.as(JsonPlaceHolderPojo.class);//bunu ayni class ile variable icine atalim

        Assert.assertEquals(expectedDataObject.getCompleted(),actualDataObject.getCompleted());
        Assert.assertEquals(expectedDataObject.getTitle(),actualDataObject.getTitle());
        Assert.assertEquals(expectedDataObject.getUserId(),actualDataObject.getUserId());
    }
}
