package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//Bilinmeyen degerler oldugunda onlari gormezden gel bana hata verme demek oluyor
//*****************************METAFOR ile ACIKLAMA****************************

//benim kutumda "100 dolar" al girisi var, ama ben kutuya "yazılı 100 dolar" koymak istiyorum
//Java bana diyecek ki senin koymak istedigin "yazılı 100 dolar" ama benim kutuya giris "100 dolar" ee nasıl olacak.
//Ben bunu gormezden gel demek istiyorum nasıl diyorum @JsonIgnoreProperties(ignoreUnknown = true) ile bunu diyince
// hoop "yazılı 100 dolar" kutuya "yazılı" kısmını görmeden giriyor.

//*****************************Gercek ile Aciklama*****************************

//Simdi benim burada olustudugum bir Class(JsonPlaceHolderPojo) ve bu class(JsonPlaceHolderPojo)'in icinde id yok
//Ama ben response class'ini bu class'a benzetmek istiyorum. Benzetemem ki response'un icinde id var bu class(JsonPlaceHolderPojo)'da yok
//Response body'yi, JsonPlaceHolderPojo class'ına benzetmek icin id'yi gormezden gelelim.Yani JsonPlaceHolderPojo'da olmayanlari gorme isimizi zora sokma diyor
//@JsonIgnoreProperties(ignoreUnknown = true) annotation ile yapariz.

public class JsonPlaceHolderPojo {
    /*
        1)Create private variables for every key
        2)Create constructor with all parameters, and without any parameter
        3)Create getters and setters
        4)Create toString() method
     */

    // 1)Create private variables for every key
    private Integer userId;
    private String title;
    private Boolean completed;

    //2)Create constructor with all parameters, and without any parameter

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    //        3)Create getters and setters
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    //      4)Create toString() method
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
    /*
        How do you handle different key-values in response body?
        I use @JsonIgnoreProperties(ignoreUnknown = true) at the top of the Pojo Class, it comes from
        "org.codehaus.jackson.annotate.JsonIgnoreProperties;"
    */
}
