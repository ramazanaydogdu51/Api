package GetRequests;


import BaseUrls.RestfulBooker_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01_tekrar extends RestfulBooker_BaseUrl { /*
        1)  Postman manuel Api testi icin kullanılır.
        2)  Api otomasyon testi icin Rest kullanilir- Assured Library
        3)  Otomasyon kodlarinin yazimi icin su adimlari izliyoruz
            A)  Gereksinimleri anlamak
            B)  Test Case'i yazmak
                    *)Test Case yazımı icin "Gherkin Language" kullanıyoruz
                         -)Given: on kosullar
                         -)When: yapacagimiz aksiyonlar-> Get,Put
                         -)Then: donutler,assert,cevap...
                         -)And:  coklu islemler icin then'den sonra
            C)  Testing kodunun yazimi:
                         1)Set the URL  (URL kurulumu),
                         2)Set the expected data (POST-PUT-PATCH) Beklenen datanın kurulumu,
                         3)Type code to send request (Talep gondermek icin kod yazmak),
                         4)Do Assertion  (Dogrulama-test yapmak).
     */

     /*
     ***********TASK (Test Case)**************
      1  Given
            https://restful-booker.herokuapp.com/booking/1004
      2  When
            User sends a GET Request to the url
      3  Then
            HTTP Status Code should be 200
      4  And
            Content Type should be JSON
      5  And
            Status Line should be HTTP/1.1 200 OK

           */

    @Test
    public void tes01() {
        //1)
        //                           booking/                    1004
        //              isimAtandı,/gidilenyer/ isimAtandi, /gidilenyer2/
        spec.pathParams("first","booking","second",1005);//spec varaible'ı Parentten geldi ona yollar ekledik

        //2)
        Response    response=   given().spec(spec).when().get("/{first}/{second}");
        //devami icin response'e ekledik islemi sonra 3'te devam edeyecegiz
        response.prettyPrint();//response body'nin icindekileri yazdirir
        //3)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        //Test Passed


        System.out.println("Status code =>  "+response.statusCode());
        System.out.println("contentType =>  "+response.contentType());
        System.out.println("statusLine =>  "+response.statusLine());
        System.out.println("response.headers() = " + response.headers());

    }



}
