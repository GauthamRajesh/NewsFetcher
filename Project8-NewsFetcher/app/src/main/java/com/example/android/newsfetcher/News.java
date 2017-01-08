package com.example.android.newsfetcher;

public class News {
    private String mTitle;
    private String mCategory;
    public News(String title, String category) {
        mTitle = title;
        mCategory = category;
    }
    public String getTitle() {
        return mTitle;
    }

    public String getCategory() {
        return mCategory;
    }
}
