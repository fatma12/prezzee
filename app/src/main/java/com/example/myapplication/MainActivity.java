package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "http://arey-static-site.s3-website-ap-southeast-2.amazonaws.com/data.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Card> lstCard ;
    private RecyclerView recyclerView ;
    Button button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        lstCard = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();


        final Context context = this;
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyWalletActivity.class);
                startActivity(intent);
            }
        });}


    private void jsonrequest() {
        JsonObjectRequest jsObjRequest =new JsonObjectRequest(Request.Method.GET,JSON_URL, null,
                new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });


    request = new JsonObjectRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject cardObject = response.getJSONObject(i);
                        Card card = new Card();
                        card.setName(cardObject.getString("name"));
                        card.setCurrency(cardObject.getString("Currency"));
                        card.setValue(cardObject.getInt("value"));
                        card.setTheme(cardObject.getString("theme"));
                        lstCard.add(card);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                    setuprecyclerview(lstCard);

            }
        },


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Card> lstCards) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstCard) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }
}


