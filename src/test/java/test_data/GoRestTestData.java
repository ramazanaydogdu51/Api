package test_data;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {


   public Map<String, Object> dataKeyAllMap(Integer id,String name,String email,String gender,String status){
        Map<String,Object> dataKeyMap=new HashMap<String,Object>();
        dataKeyMap.put("id",id);
        dataKeyMap.put("name",name);
        dataKeyMap.put("email",email);
        dataKeyMap.put("gender",gender);
        dataKeyMap.put("status",status);

        return dataKeyMap;
    }

    public Map<String, Object> expectedBodyMap(Object meta,Map<String,Object>dataKeyAllMap){
        Map<String,Object> expectedBodyMap=new HashMap<String,Object>();
        expectedBodyMap.put("meta",meta);
        expectedBodyMap.put("data",dataKeyAllMap);


        return expectedBodyMap;
    }

}
