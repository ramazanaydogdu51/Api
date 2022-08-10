package GetRequests;

import BaseUrls.JsonPlaceholder_BaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Get07 extends JsonPlaceholder_BaseUrl {
    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void test01() {
        spec.pathParam("first","todos");
        //When
        //			 I send GET Request to the URL
        Response response =
        given().spec(spec).when().get("/{first}");
        //2)Print all ids greater than 190 on the console
        //  Assert that there are 10 ids greater than 190
       response.prettyPrint();
        // 1)Status code is 200
        response.then().assertThat().statusCode(200);
        // 2)Print all ids greater than 190 on the console
         //    Assert that there are 10 ids greater than 190

        JsonPath json= response.jsonPath();  //response'ın icindekileri json'a donusturerek json objesine attık

                                             //bu kısım filtre.bu kisim bunu getir dedigimiz yer
        List<Integer>ids = json.getList("findAll{it.id>190}.id");// Groovy language: Java Based programming language
        //Hey java findALL, tamam ama hangilerini   => (it yani bu json'da) {it.id>190} id'si 190'dan büyük olanı
        // en sondaki      .id          bana id'leri ver demek
        //en sondaki      .title         bana title'leri ver demek
        System.out.println("ids = " + ids);
        //***Assert that there are 10 ids greater than 190
        Assert.assertEquals(10,ids.size()); //10 tane id var mı 190 dan buyuk 10 tane var
    //    Assert.assertEquals("10 tane id yok 190'dan buyuk",11,ids.size()); //11 tane id var mı 190 dan buyuk 11 tane yok

        //3)Print all userIds whose ids are less than 5 on the console
        List<Integer>  userIds=json.getList("findAll{it.id<5}.userId");
        System.out.println("userIds = " + userIds);

        // Assert that the number of userIds whose ids are less than 5 is 4
        Assert.assertEquals("id'si 5 ten kucuk 4 tane sayi yoktur",4,userIds.size());
        //4)Print all titles whose ids are less than 5
        //  Assert that "delectus aut autem" is one of the titles whose id is less than 5
        List<String> titles=json.getList("findAll{it.id<5}.title");
        System.out.println("titles = " + titles);
        boolean isHas=titles.contains("delectus aut autem");
       Assert.assertTrue("hayir icermiyor",isHas);
        Assert.assertTrue("hayir icermiyor",titles.stream().anyMatch(t-> t.contains("delectus aut autem")));

    }
}
