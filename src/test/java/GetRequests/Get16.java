package GetRequests;

import BaseUrls.DummyRestApiBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.*;

public class Get16 extends DummyRestApiBaseUrl {
     /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
        Given
           https://dummy.restapiexample.com/api/v1/employees
        When
            User send GET Request to the URL
        Then
            Status code is 200
        And
            There are 24 employees
        And
            "Tiger Nixon" and "Garrett Winters" are among the employees
        And
            The greatest age is 66
        And
           The name of the lowest age is "[Tatyana Fitzpatrick]"
        And
           Total salary of all employees is 6,644,770
     */

    @Test
    public void name() {

        //1 Set the url
        spec.pathParams("first","employees");

        //2     Set the Expected Data



        //3     Send the Get Request and get the Response
        Response response=
        given().spec(spec)

                // HTML verdigi icin bunu böyle yaptim JSON versin diye
                .when().get("/{first}");

        response.prettyPrint();

        //4     Do Assertion
        response.then().assertThat()
                //.statusCode(200)
                .body("data.id", Matchers.hasSize(24));

        //Assert the existence of specific data
        response.then().assertThat().body("data.employee_name",Matchers.hasItems("Tiger Nixon","Airi Satou"));


        // Assert the greatest age
        JsonPath json = response.jsonPath();
        List<Integer> ageList=json.getList("data.findAll{it.id>0}.employee_age"); //id'si 0dan buyuk olanların yaslarini getir
        // butun datalari list'e attik
        //oradan en buyuk yasin 66 ya esit oldugunu assert edelim
        System.out.println("ageList = " + ageList);
        Collections.sort(ageList);
        System.out.println("ageListSort = " + ageList);

        Assert.assertEquals(66,(int) ageList.get(ageList.size()-1));
        // Assert the name of the lowest age is "[Tatyana Fitzpatrick]"
        String groovyString="data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name";

        //String minAgeEmployeeName= json.getString("data.findAll{it.employee_age==19}.employee_name");// hardcoding
        String minAgeEmployeeName= json.getString(groovyString);
        System.out.println("minAgeEmployeeName = " + minAgeEmployeeName);
        Assert.assertEquals("[Tatyana Fitzpatrick]", minAgeEmployeeName);
        // Assert           Total salary of all employees is 6,644,770
        List<Integer> salaryList=json.getList("data.findAll{it.id>0}.employee_salary");
        System.out.println("salaryList = " + salaryList);


       // double sum=0;
       // for (Integer w:salaryList){
       //    sum=sum+w;
       // }
        int sum=        salaryList.stream().reduce(0,(x,y)->(x+y));
        int sum2 =salaryList.stream().reduce(0,Integer::sum);
        int sum3 =salaryList.stream().reduce(0,Math::addExact);
        System.out.println("sum = " + sum);
        Assert.assertEquals(6644770,sum3);

    }
}
