package BaseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyRestApiBaseUrl {
        //https://dummy.restapiexample.com/api/v1/employees
    protected RequestSpecification spec;
    //kodlari ufak ufak parcalayip bolmek atomic structure(atomik yapi)
    // @Before annotation'ı kullandığımız methodlar her  Test metodondan önce çalıştırılır.

    @Before//her test method'undan once calissin diye
    public void setUp(){
        //new kelimesinden sonra Cons gelir,Eger interface ise alt classlardan alir
        spec = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1").build();
    }





}
