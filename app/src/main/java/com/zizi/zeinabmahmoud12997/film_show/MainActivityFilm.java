package com.zizi.zeinabmahmoud12997.film_show;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityFilm extends AppCompatActivity implements ExampleAdapter.OnItemClickListner {


    public static final String EXTRA_URL = "img";
    public static final String EXTRA_NAME = "filmName";
    public static final String EXTRA_CAT = "cat";
    public static final String EXTRA_DETAIL = "des";
    public static final String EXTRA_FILM = "film";


    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExamplItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_film);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {

        String url = "http://192.168.43.233/android_register_login/film.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject hit = jsonArray.getJSONObject(i);
                        String name = hit.getString("filmName");
                        String imgURL = hit.getString("img");
                        String cat = hit.getString("cat");
                        String des = hit.getString("des");
                        String film = hit.getString("film");
                        mExampleList.add(new ExamplItem(imgURL, name, cat, des, film));
                    }

                    mExampleAdapter = new ExampleAdapter(MainActivityFilm.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                    mExampleAdapter.setOnItemClickListner(MainActivityFilm.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(request);

    }


    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(MainActivityFilm.this, DetailActivity.class);
        ExamplItem clickItem  = mExampleList.get(position);


        detailIntent.putExtra(EXTRA_URL, clickItem.getmImageURL());
        detailIntent.putExtra(EXTRA_NAME, clickItem.getFilmName());
        detailIntent.putExtra(EXTRA_CAT, clickItem.getCat());
        detailIntent.putExtra(EXTRA_DETAIL, clickItem.getDet());
        detailIntent.putExtra(EXTRA_FILM, clickItem.getFilm());
        startActivity(detailIntent);
    }
}
