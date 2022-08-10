package GetRequests;

import BaseUrls.GoRest_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;
import test_data.GoRestTestData;

import static io.restassured.RestAssured.*;

public class Get13Pojo extends GoRest_BaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                                {
                                     "meta": null,
                                     "data": {
                                         "id": 13,
                                         "name": "Mani Kaur",
                                         "email": "mani_kaur@kiehn.co",
                                         "gender": "male",
                                         "status": "inactive"
                                             }
                                }
    */


    @Test
    public void test01() {
    //Set the URL
        spec.pathParams("first","users","second",13);
    //Set the expected data
        GoRestDataPojo expectedInnerData = new GoRestDataPojo("Mani Kaur","mani_kaur@kiehn.co","male","inactive");
        GoRestPojo expectedBodyData = new GoRestPojo(null,expectedInnerData);

    //Send the Request and Get the response
        Response response=
        given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    //Response body convert to GoRestPojo Class
        GoRestPojo actualBodyData=  response.as(GoRestPojo.class);
        Assert.assertEquals(expectedBodyData.getMeta(),actualBodyData.getMeta());
        Assert.assertEquals(expectedBodyData.getData().getEmail(),actualBodyData.getData().getEmail());
        Assert.assertEquals(expectedBodyData.getData().getGender(),actualBodyData.getData().getGender());
        Assert.assertEquals(expectedBodyData.getData().getName(),actualBodyData.getData().getName());
        Assert.assertEquals(expectedBodyData.getData().getStatus(),actualBodyData.getData().getStatus());

    }
}
