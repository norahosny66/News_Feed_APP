package com.example.news;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.example.news.QueryUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class News_Loader extends AsyncTaskLoader<List<News>> {

    String mUrl;
    private static List<News> news  ;

    public static List<News> getNews() {
        return news;
    }

    public News_Loader(@NonNull Context context) {
        super(context);
    }

    public News_Loader(@NonNull Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        if (mUrl==null) return null;

        String jsonResponse=  QueryUtils.MakeHTTPRequest(this.mUrl);
        news=  new ArrayList<>();
        return QueryUtils.extractDataFromJson(jsonResponse,news);
    }
}
