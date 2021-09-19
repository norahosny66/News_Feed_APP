package com.example.news;
import com.example.news.MainActivity;
import android.os.Bundle;

import static android.app.PendingIntent.getActivity;


public class Category {
    String Url;
    String PillarName;

    public Category(String url, String name) {
        Url = url;
        PillarName = name;
    }


}
