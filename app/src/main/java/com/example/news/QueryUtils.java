package com.example.news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    public static String MakeHTTPRequest(String url_based_on_category) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        String line;
        try {
            URL url = new URL(url_based_on_category);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()!=200) return null;
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            // read from stream
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) return null;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (buffer.length() == 0) return null;
            jsonStr = buffer.toString();// json response
            // read from stream/////////////////////
        } catch (IOException e) {
            Log.e("MainActivity", "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
        }

        return  jsonStr;
    }

     public static List<News> extractDataFromJson (String jsonResponse,List<News> newsList) {

        try {
            JSONObject json = new JSONObject(jsonResponse);
            JSONObject response = json.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentNews = results.getJSONObject(i);
                String title = currentNews.getString("webTitle");
                String section = currentNews.getString("sectionId");
                String date = currentNews.getString("webPublicationDate");
                String url = currentNews.getString("webUrl");
                String Pillar_name = currentNews.getString("pillarName");
                String Name = currentNews.getString("sectionName");

                News newsObject = new News();
                newsObject.setSectionID(section);
                newsObject.setTitle(title);
                newsObject.setDate(date);
                newsObject.setUrl(url);
                newsObject.setPillarName(Pillar_name);
                newsObject.setSectionName(Name);
                newsList.add(newsObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
