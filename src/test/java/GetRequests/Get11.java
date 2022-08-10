package GetRequests;

import BaseUrls.GoRest_BaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Get11 extends GoRest_BaseUrl {
             /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send Get Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should be 10
    And
        We have at least one "active" status
    And
        "Deeptimayee Butt", "Gopi Marar" ,"Bakula Ahuja" are among the users
    And
        The female users are mor than male users
          */


    @Test
    public void test01() {
        //1     Set the URL
        spec.pathParams("first","users");

        //2     Set the expected data


        //3     Send the request and Get the Response
        Response response=
        given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //System.out.println("response.asString().contains(\"Deeptimayee Butt\") = " + response.asString().contains("Deeptimayee Buttx"));


        //4     Do Assertion

            //1.Way  By using body()

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("meta.pagination.limit", Matchers.equalTo(10))
                .body("meta.pagination.links.current",Matchers.equalTo("https://gorest.co.in/public/v1/users?page=1"))
                //soruda bize kullanici sayisini su kadar oldugunu bulun diyor biz teker teker saysak buluruz ama otomasyona dokelim "hasSize" ile
                //bunu id ile yapalim hepsinde id vardır
                //body'nin icindeki "data" bize list veriyor cunku orada [ ile açıp ] ile kapamış
                .body("data[0].id",Matchers.equalTo(2532))  //data list'inin 0. indexindeki id si 2532 ye esit mi?
                .body("data.id",Matchers.hasSize(10))
                .body("data.status",Matchers.hasItem("active"))
                .body("data.name",Matchers.hasItems("Divakar Panicker","Anuraag Tagore","Dakshayani Tagore"));
        // I will compare the number of female and male users in 2 ways, kadinlar mi daha buyuk icin 2 yolum var
        //i)I will get all genders then I will count the females then I will calculate males then I will check wich one is more
                //once bunlari jsonPath() methodu ile convert edip JsonPath class'ından faydalanmasini saglayacagım
        JsonPath json=response.jsonPath();
        List<String> genderList=json.getList("data.gender");
        System.out.println("genderList = " + genderList);
        //buradan female olanların sayısını bulalım
        int femaleCount=0;
        for (String w:genderList){
            if (w.equals("female")){
                femaleCount++;
            }
        }
        System.out.println("femaleCount = " + femaleCount);//4
        //kizlarin sayısını bulduk erkeklerinki =>      genders-kızlar  =   erkekler
        int maleCount=genderList.size()-femaleCount;
        System.out.println("maleCount = " + maleCount);
        Assert.assertTrue(femaleCount<maleCount);

        //ii) I will get all females by usin Groovy, I will get all males by using Groovy then compare them
        //  List<String> femaleList=json.getList("data.findAll{it.gender='female'}");
            //****** boyle yazınca filtreliyor female olanların hepsini yani diger bilgileri de getiriyor SQL'deki gibi
        JsonPath json2=response.jsonPath();
        List<String> femaleList=json2.getList("data.findAll{it.gender='female'}.gender");//olmadi bu
        System.out.println("femaleList = " + femaleList);
    }
}
