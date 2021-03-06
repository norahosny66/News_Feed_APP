package com.example.news;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private RecyclerView recyclerView ;
    News_Loader news_loader ;
    RecycleView_Adapter  mAdapter;
    ProgressBar loadingIndicator ;
    TabLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Design_Bars();
        loadingIndicator =(ProgressBar) findViewById(R.id.loadingIndicator);
        loadingIndicator.setVisibility(View.VISIBLE);
        tab =(TabLayout) findViewById(R.id.tabLayout);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
       
            Loading_News();
        }

        else{
            System.out.println("no internet");
            loadingIndicator.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Sorry,no internet connectivty",Toast.LENGTH_LONG).show();

        }
        recyclerView =(RecyclerView) findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
             public void load(int LOUDER_ID){
        getSupportLoaderManager().initLoader(LOUDER_ID,null,this);
                  }

    public News_Loader onCreateLoader(int id, Bundle args) {

         if(id==0)
             news_loader = new News_Loader(this,Constants.SPORT_REQUEST_URL);
         else if(id==1)
             news_loader = new News_Loader(this,Constants.NEWS_REQUEST_URL);
         else if(id==2)
             news_loader = new News_Loader(this,Constants.POLITICS_REQUEST_URL);
         else if(id==3)
             news_loader = new News_Loader(this,Constants.ARTICLES_REQUEST_URL);
         else if(id==4)
             news_loader = new News_Loader(this,Constants.WORLD_REQUEST_URL);
         else if(id==5)
             news_loader = new News_Loader(this,Constants.GLOBAL_DEVELOPMENT_REQUEST_URL);

        return news_loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        loadingIndicator.setVisibility(View.GONE);
        mAdapter = new RecycleView_Adapter(this,data);
          recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) { }

    public void Design_Bars(){
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#344955"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    public void Loading_News(){
        getSupportLoaderManager().initLoader(0, null, this);
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("position  " + tab.getPosition());
                if (tab.getPosition() == 0) {
                    news_loader = new News_Loader(tab.view.getContext(), Constants.SPORT_REQUEST_URL);
                    load(0);
                } else if (tab.getPosition() == 1) {
                    news_loader = new News_Loader(tab.view.getContext(), Constants.NEWS_REQUEST_URL);
                    load(1);
                } else if (tab.getPosition() == 2) {
                    news_loader = new News_Loader(tab.view.getContext(), Constants.POLITICS_REQUEST_URL);
                    load(2);
                } else if (tab.getPosition() == 3) {
                    news_loader = new News_Loader(tab.view.getContext(), Constants.ARTICLES_REQUEST_URL);
                    load(3);
                } else if (tab.getPosition() == 4) {
                    news_loader = new News_Loader(tab.view.getContext(), Constants.WORLD_REQUEST_URL);
                    load(4);
                } else if (tab.getPosition() == 5) {
                    news_loader = new News_Loader(tab.view.getContext(), Constants.GLOBAL_DEVELOPMENT_REQUEST_URL);
                    load(5);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}