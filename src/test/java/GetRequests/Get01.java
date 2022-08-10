package GetRequests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
// SUPER BILGI     io.restassured.RestAssured.*
// asteriks diyince given then vb'ni import etti
public class Get01 {
    /*
        1)  Postman manuel Api testi icin kullanılır.
        2)  Api otomasyon testi icin Rest - Assured Library
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
            https://restful-booker.herokuapp.com/booking/51
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
    public void get01() {
        //A)Set the URL  (URL kurulumu),
                // 1  Given
                //https://restful-booker.herokuapp.com/booking/3
                String url="https://restful-booker.herokuapp.com/booking/56";

        // B)Set the expected data (POST-PUT-PATCH) Beklenen datanın kurulumu-olusturulmasi,
                //burayı neden atladik? Cunku POST PUT PATCH ile isimiz yok get ile var.


        // C)Type code to send request (Talep gondermek icin kod yazmak),
                Response response=  given().
                                     when().        //2  When
                                     get(url);      //User sends a GET Request to the url

          response.prettyPrint();//response body'nin icindekileri yazdirir
        // D)Do Assertion  (Dogrulama-test yapmak).
        response.
                then().//3  Then
                assertThat().//Dogrulamak icin keyword
                statusCode(200).//3  Then ->    HTTP Status Code should be 200
                contentType("application/json").//4  And -> Content Type should be JSON
                statusLine("HTTP/1.1 200 OK");//5  And ->   Status Line should be HTTP/1.1 200 OK
        //API testi bitti



        //Test gecti ama biz bunlari ekranda gormek istiyoruz

        //'Status Code' nasıl yazdırılır:
        System.out.println("Status Code: "+response.statusCode());

        //'Content Type' nasıl yazdırılır:
        System.out.println("Content Type: "+response.contentType());

           //'Status Line' nasıl yazdırılır:

        System.out.println("Status Line: "+response.statusLine());

        //'Header' nasıl yazdırılır:

        System.out.println(response.header("User-Agent"));

        //'Headers' nasıl yazdırılır:
        System.out.println("Headers:\n"+response.headers());

        //'Time' nasıl yazdırılır:
        System.out.println("Time: "+response.getTime());

    }
}
