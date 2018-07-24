package com.example.mlabsystem2.dialerfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Newsfeed extends AppCompatActivity {

    String API_KEY = "3ef9a0a52d4b4d61975877cc0fbb990c"; // ### YOUE NEWS API HERE ###
    String[] NEWS_SOURCE = {"bbc-news", "abc-news", "axios", "bbc-sport", "blasting-news-br", "bloomberg", "cbc-news", "cnbc", "cnn","daily-mail",
    "business-insider","espn","financial-times","google-news","fox-news","hacker-news","info-money","mirror","mtv-news","national-geographic","news24",
    "new-scientist","politico","time","the-hindu"};
    RecyclerView listNews;
    ProgressBar loader;
    private RecyclerAdapterfeed adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        listNews = (RecyclerView) findViewById(R.id.listNews);
        loader = (ProgressBar) findViewById(R.id.loader);



        for(int i=0;i<NEWS_SOURCE.length;i++) {
            String m = String.valueOf(i);
            DownloadNews newsTask = new DownloadNews();

            if (Function.isNetworkAvailable(getApplicationContext())) {

                newsTask.execute(m);
            } else {
                if(i==1) {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    loader.setVisibility(View.GONE);
                }
            }
        }



    }


    class DownloadNews extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            String urlParameters = "";
            String xml = "";
            int l = Integer.parseInt(args[0]);


            xml = Function.excuteGet("https://newsapi.org/v2/top-headlines?sources=" + NEWS_SOURCE[l] + "&apiKey=" + API_KEY, urlParameters);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {


            if (xml.length() > 10) { // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR).toString());
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE).toString());
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION).toString());
                        map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                        map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT).toString());
                        if (((jsonObject.optString(KEY_TITLE).toString()).indexOf("get") != -1)||((jsonObject.optString(KEY_DESCRIPTION).toString()).indexOf("take") != -1)) {
                            dataList.add(map);
                        }
                        if(dataList.size()!=0){
                            loader.setVisibility(View.GONE);
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }

                layoutManager=new LinearLayoutManager(Newsfeed.this, LinearLayoutManager.VERTICAL,false);
                listNews.setHasFixedSize(true);
                listNews.setLayoutManager(layoutManager);
                adapter=new RecyclerAdapterfeed(dataList,Newsfeed.this);
                listNews.setAdapter(adapter);
              //  RecyclerAdapterfeed adapter = new RecyclerAdapterfeed(dataList,PatientHome.this);
                //listNews.setAdapter(adapter);

//                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent, View view,
//                                            int position, long id) {
//                        Intent i = new Intent(PatientHome.this, DetailsActivity.class);
//                        i.putExtra("url", dataList.get(+position).get(KEY_URL));
//                        startActivity(i);
//                    }
//                });


            } else {
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
                loader.setVisibility(View.GONE);
            }


        }
    }
}





