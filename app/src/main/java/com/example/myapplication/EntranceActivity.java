package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class EntranceActivity extends MainActivity {


    private static int TIME_OUT = 4000;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View myLayout = findViewById(R.id.enter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(EntranceActivity.this,MainActivity.class);
                startActivity(intent);
                EntranceActivity.this.finish();
            }
        },TIME_OUT);
    }

}
