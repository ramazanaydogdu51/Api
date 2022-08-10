package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfullBookerTestData {
//booking date String, String diye
    public Map<String ,String> bookingDatesSetUp (String checkin, String checkout){

        Map<String , String > bookingDatesMap= new HashMap<String ,String>();
        bookingDatesMap.put("checkin",checkin);
        bookingDatesMap.put("checkout",checkout);
        return bookingDatesMap;
    }
//Key'ler String, Value'ler karisik ondan Object
    public Map <String, Object> expectedDataSetUp
    (String firstname,String lastname, int totalprice,boolean depositpaid,Map<String,String> bookingdates){
        Map<String,Object> expectedDataMap = new HashMap<String,Object>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);
        return expectedDataMap;
    }
}
