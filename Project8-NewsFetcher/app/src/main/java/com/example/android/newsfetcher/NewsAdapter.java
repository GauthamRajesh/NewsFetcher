package com.example.android.newsfetcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context c, ArrayList<News> news) {
        super(c, 0, news);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }
        News news = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(news.getTitle());
        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(news.getCategory());
        return listItemView;
    }
}
