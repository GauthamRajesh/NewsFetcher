package com.example.android.newsfetcher;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<News> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        return Network.getNewsData(mUrl);
    }
}
