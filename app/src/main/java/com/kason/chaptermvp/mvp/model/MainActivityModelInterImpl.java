package com.kason.chaptermvp.mvp.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kason_zhang on 11/16/2016.
 */

public class MainActivityModelInterImpl implements MainActivityModelnter {
    private HttpURLConnection httpURLConnection;
    @Override
    public String getNetworkData() {
        String response = null;
        try {
            URL url = new URL("https://www.baidu.com");
            httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            //int available = inputStream.available();
            response = getStringFromInputStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
    private String getStringFromInputStream(InputStream is){
        String state = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;

        try {
            while((len = is.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
            state = os.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }
}
