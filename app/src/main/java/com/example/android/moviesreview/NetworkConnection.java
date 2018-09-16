package com.example.android.moviesreview;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
/*Make Internet Connection*/
public class NetworkConnection {
    final static String MAIN_URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    final static String QUERY = "query";
    final static String API_KEY = "api-key";
    public URL buildUrl(String i){
        Uri buildUri;
        buildUri = Uri.parse(MAIN_URL).buildUpon()
                .appendQueryParameter(API_KEY,"47b5a671bf3749a7b5badfb5e7ef7412")
                .appendQueryParameter(QUERY,i).build();
        URL url = null;
        try{
            url = new URL(buildUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }
    public String getRespond(String i) throws IOException {
        URL url = buildUrl(i);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        try{
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner((inputStream),"UTF-8");
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            } else {
                return null;
            }
        }finally {
            connection.disconnect();
        }
    }
}
