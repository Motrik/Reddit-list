package com.atelierdesign.tiago.listfromjson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.atelierdesign.tiago.listfromjson.database.RedditDAO;
import com.atelierdesign.tiago.listfromjson.model.Listing;
import com.atelierdesign.tiago.listfromjson.model.Post;
import com.google.gson.Gson;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ListActivity extends ActionBarActivity implements RedditAdapter.ItemClick {

    // public static final String REDDIT_URL = "http://www.reddit.com/r/all.json?limit=25";  http://api.feedzilla.com/v1/cultures.json
    public static final String FEEDZILLA_URL = "http://www.reddit.com/r/all.json?limit=50"; //"http://api.feedzilla.com/v1/cultures.json"
    public static final String BASE_URL = "http://www.reddit.com/r/";
    public static final String URL_LIMIT = ".json?limit=50";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String url = FEEDZILLA_URL;

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        // add the custom view to the action bar
        actionBar.setCustomView(R.layout.actionbar);
        final EditText search = (EditText) actionBar.getCustomView().findViewById(R.id.searchfield);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);  //RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

// Instantiate the RequestQueue.  - A ideia do volley é partilhar o "qeue" €m toda a aplicação - o ideal é criar uma "singleton"
        // RequestQueue queue = ConnectionManager.getInstance(this);
        TextView.OnEditorActionListener tv = new TextView.OnEditorActionListener() {
            String innerurl = FEEDZILLA_URL;

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(ListActivity.this, "Search triggered",
                        Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(search.getText())) {
                    innerurl = BASE_URL + search.getText() + URL_LIMIT;

                }

                 Connection(innerurl);

                return false;
            }
        };
        search.setOnEditorActionListener(tv);

        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);



        Connection(url);


    }

    public void Connection(String murl) {
        String url = murl;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                      //  mRecyclerView.invalidate();

                        Listing listing = new Gson().fromJson(response, Listing.class);

                        //a escrever na db
                        List<Post> postList = listing.getPostList();


                        RedditAdapter adapter = new RedditAdapter(postList, ListActivity.this, ListActivity.this);

                        RedditDAO.getInstance().storePosts(ListActivity.this, postList);

                        mRecyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // se houver erro retorna a lista da DB
                List<Post> postList = RedditDAO.getInstance().getPostFromDB(ListActivity.this);

                RedditAdapter adapter = new RedditAdapter(postList, ListActivity.this, ListActivity.this);
                mRecyclerView.setAdapter(adapter);
            }


        });
        // queue.add(stringRequest); // Add the request to the RequestQueue.
        ConnectionManager.getInstance(this).add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
     //   finish();
    }


    @Override
    public void OnItemClicked(Post CardClicked) {
        //TODO abrir uma webview

        Toast.makeText(ListActivity.this, "item clicked: " + CardClicked.getTitle(), Toast.LENGTH_SHORT).show();
        Log.d("REGISTO", "item clicked: " + CardClicked.getTitle());

        Intent intent = new Intent(ListActivity.this, WebActivity.class);
        intent.putExtra("URL", CardClicked.getPermalink());

        startActivity(intent);
    }


    // fonts injectaDAS
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}



