package com.example.myhomework3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {
    private ImageButton button_first;
    private ImageButton button_second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
            button_first = findViewById(R.id.btn2);
            button_second = findViewById(R.id.btn1);
            button_second.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(SearchActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });
            button_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(SearchActivity.this,CompareActivity.class);
                    startActivity(intent);
                }
            });
    }
}