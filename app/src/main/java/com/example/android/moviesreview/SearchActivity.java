package com.example.android.moviesreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {
    String[] moviesNames, reviewers, urls, summary, publication, mpaa;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String json = (String) getIntent().getExtras().getString("str");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);
        JsonData jsonData = new JsonData();
        moviesNames = jsonData.getNames(json);
        reviewers = jsonData.getReviewers(json);
        urls = jsonData.getUrls(json);
        summary = jsonData.getSummaries(json);
        publication = jsonData.getPublication(json);
        mpaa = jsonData.getMPAAs(json);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, this, moviesNames, reviewers, urls, summary, publication, mpaa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
