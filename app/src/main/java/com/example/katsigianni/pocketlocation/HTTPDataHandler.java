package com.example.katsigianni.pocketlocation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by KonstantinaKats on 26-Nov-17.
 */

public class HTTPDataHandler {
    static String stream=null;

    public HTTPDataHandler(){

    }

    public String GetHTTPData(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            //check the connection status
            if(urlConnection.getResponseCode() == 200){

                //if response code = 200 ~ HTTP.OK
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                //Read the BufferedInputStream
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line=r.readLine()) != null)
                    sb.append(line);

                stream = sb.toString();
                urlConnection.disconnect();
            }else{

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }

    public void PostHTTPData(String urlString, String json){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            byte[] out = json.getBytes(Charset.forName("UTF-8"));
            int length = out.length;
            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            urlConnection.connect();
            OutputStream os=null;
            try{
                os = urlConnection.getOutputStream();
                os.write(out);
            }finally {
                if(os != null){
                    os.close();
                }
            }
            InputStream response = urlConnection.getInputStream();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PutHTTPData(String urlString, String newValue){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoOutput(true);

            byte[] out = newValue.getBytes(Charset.forName("UTF-8"));
            int length = out.length;
            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            urlConnection.connect();
            OutputStream os=null;
            try{
                os = urlConnection.getOutputStream();
                os.write(out);
            }finally {
                if(os != null){
                    os.close();
                }
            }
            InputStream response = urlConnection.getInputStream();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteHTTPData(String urlString, String json){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoOutput(true);

            byte[] out = json.getBytes(Charset.forName("UTF-8"));
            int length = out.length;
            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            urlConnection.connect();
            OutputStream os=null;
            try{
                os = urlConnection.getOutputStream();
                os.write(out);
            }finally {
                if(os != null){
                    os.close();
                }
            }
            InputStream response = urlConnection.getInputStream();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
