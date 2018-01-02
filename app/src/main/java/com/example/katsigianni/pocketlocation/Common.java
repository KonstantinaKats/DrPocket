package com.example.katsigianni.pocketlocation;

/**
 * Created by KonstantinaKats on 26-Nov-17.
 */

public class Common {

    private static String DB_NAME="drpocketapp";
    private static String COLLECTION_USER="Users";
    public static String API_KEY="3kwlMoz7yqq2POGxx9XIJVVvkAZfyAQa";

    public static String getAddressSingle(String surname, String personal_name){
        String baseUrl= String.format("https://api.mlab.com/api/1/databases/"+DB_NAME+"/collections/"+COLLECTION_USER+"");
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl= String.format("https://api.mlab.com/api/1/databases/"+DB_NAME+"/collections/"+COLLECTION_USER+"");
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
}
