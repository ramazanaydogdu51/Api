package GetRequests;

import BaseUrls.BaseUrl;

import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class GetTest extends BaseUrl {

    @Test
    public void test01() {
        //{{baseUrl}}/pet/findByStatus?status=available&status=available

       spec.pathParams("first","pet",
               "second","findByStatus");
        String url="https://petstore.swagger.io/v2/pet/findByStatus?status=available&status=available";
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
    }
}
