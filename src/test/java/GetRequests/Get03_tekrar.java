package GetRequests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Get03_tekrar extends JsonPlaceholder_BaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",                //body methodu ile equalTo
      And
          "completed" is false                                                                  //body methodu ile equalTo
      And
          "userId" is 2                                                                         //body methodu ile equalTo
     */

    @Test
    public void tes01() {
       //Given
       //https://jsonplaceholder.typicode.com/todos/23
       spec.pathParams("first","todos","second",23);//spec varaible'ı Parentten geldi ona yollar ekledik
        // When
        // User send GET Request to the URL
        Response response =                    given().spec(spec).when().get("/{first}/{second}");
        //Then
        //HTTP Status Code should be 200
        response.then().
                assertThat()
                .statusCode(200)//HTTP Status Code should be 200
                .contentType("application/json").//Response format should be "application/json"
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false))// "completed" is false
                .body("userId",equalTo(2));//"userId" is 2
        response.prettyPrint();
        //"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
    }
    /*
        Note 1: Assert yaparken Java calismayi durdurdugunda hata sonrasi kodlar calismaz.
                Boylece hata sonrasi assertionlar hakkinda bilgi sahibi olamayiz
                Fakat hata oncesi assertion'lar gecmistir.

        Note 2: Eger kodumuzu hata noktasinda duracak sekilde yazarsak "Hard Assertion" yapmis oluyoruz.

        Note 3: Eger kodumuzu hata noktasinda durmayacak sekilde yazarsak "Soft Assertion" yapmis oluyoruz.

        Note 4:Eger coklu body() methodu icinde assert yaparsak "Hard Assert",
               tek body() methodu icinde assert yaparsak "Soft Assert" yapmis oluyoruz


     */
}
