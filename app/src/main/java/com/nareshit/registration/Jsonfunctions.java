package com.nareshit.registration;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Jsonfunctions {
    public static JSONObject RegData(String url,String Param){

        JSONObject jsonObject=null;

        try {
            URL baseurl=new URL(url);
            HttpURLConnection connection=
                    (HttpURLConnection) baseurl.openConnection();
            //8 conditions

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            //headers
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            //connection.setRequestProperty("","");
            connection.connect();

            OutputStream outputStream=new
                    BufferedOutputStream(connection.getOutputStream());
            outputStream.write(Param.getBytes());
            outputStream.flush();

            InputStream inputStream=new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader=new
                    BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer=new StringBuffer();
            String line;

            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            inputStream.close();

            jsonObject=new JSONObject(stringBuffer.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;

    }
}


