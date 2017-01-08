package com.example.android.newsfetcher;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private String encodedSearchQuery;
    private String url;
    private NewsAdapter adapter;
    private boolean isConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchbutton = (Button) findViewById(R.id.search_button);
        TextView searchQueryTextView = (TextView) findViewById(R.id.searchfield);
        ListView newsView = (ListView) findViewById(R.id.news_list);
        newsView.setEmptyView(findViewById(R.id.empty_list_view));
        adapter = new NewsAdapter(this, new ArrayList<News>());
        newsView.setAdapter(adapter);
        String searchQuery = searchQueryTextView.getText().toString();
        try {
            encodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8");
        }
        catch(UnsupportedEncodingException e) {
            Log.e("MainActivity", "Problem encoding URL");
        }
        url = "https://content.guardianapis.com/search?q=android&api-key=test";
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) getApplicationContext().getSystemService(Context.
                                CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(isConnected) {
                    getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
                }
            }
        });

        getSupportLoaderManager().initLoader(0, null, this);
    }
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, url);
    }
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        adapter.clear();
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }
}
