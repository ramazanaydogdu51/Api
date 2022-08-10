package GetRequests;

import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get02_tekrar extends RestfulBooker_BaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"                  bunun icindekini gormek icin response.prettyPrint(); yapilir
                                                                icindekiler gozukur
        And
            Response body does not contains "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void tes01() {//neden JUnit'ten geldi TestNG'den gelmedi JUnit basic oldugu icin az sorun cikarir diye.
        //1) URL almak
        spec.pathParams("first","booking","second","1");//spec varaible'ı Parentten geldi ona yollar ekledik

        //2) given ile basla get URL aldik
        Response response   =       given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint(); //response body'nin icindekileri yazdirir

        //3)
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //ResponseBodyData clasina gidince methodlari goruyoruz

        //4)
          boolean isNotFound    = response.asString().contains("Not Found");
        Assert.assertTrue(isNotFound);

        //5)
        boolean isTechProEd=response.asString().contains("TechProEd");
        Assert.assertFalse(isTechProEd);

        //6)
        System.out.println("response.getHeader(\"Server\") = " + response.getHeader("Server"));
        String actualServerName=response.header("Server");
        String expetedServerName="Cowboy";
        Assert.assertEquals(expetedServerName,actualServerName);

    }
}
