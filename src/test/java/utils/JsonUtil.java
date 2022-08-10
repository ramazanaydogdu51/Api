package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static  ObjectMapper mapper;

    static{
        mapper =new ObjectMapper();
    }

    //1. Method This method will convert Json Data to Java Object ( De- Serialization )
    //Class<T> cls ben hangi datayı alırsam onu kullanırım demek. map aldım map kullanırım.
    //object yapsam map aldım map'i object olarak kullarım demek bundan object yapmadik
    //Class<T> cls bunu aldım neye return edicem <T> T  buna return edicem
    //Bu ne demek hangi data turunu alırsan ona return et dinamik(generic).

    public static     <T> T          convertJsonToJavaObject(String json,Class<T> cls){// Generic Method

        /*
        Json formatindaki verileri String'e koyduk bu String'i biz istedigimiz data turune(map)'e donusturcez
        cls istenen(map) data turudur. Map'ten gelen cls data turu "T" biz buna variable atadik.
        Adı   javaResult oldu ve deger null, sonra kullanmak icin...
        ObjectMapper Class'ından mapper objesi araciligi ile readValue methodunu kullanarak  json formatiyla gelen
        json variable'ına atılan degerleri, istenen Data turune yani Map'e donusturecegiz.
        Donusturduk bu yaptiklarimizi bizim bir variable'a atmamiz gerek readValue methodu bize <T> T data turu
        dondurmekte zaten yukarıda javaResult'u buna gore null yaptık şimdi icini dolduracagiz. Dolduruldu
        Method'un retun type'ı istenen data turu yani map genel adiyla <T> T oldugundan return'e de buna gore yaptik.
         */
        T javaResult = null;

        try {
            javaResult  =   mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaResult;
    }

    //2. Method This method will convert Java Object to Json Data ( Serialization )
    public static String convertJavaObjectToJson(Object obj){

        String jsonResult = null;
        try {
            jsonResult=mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }
}
