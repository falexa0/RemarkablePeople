package com.example.rmptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateEventActivity extends AppCompatActivity {
Button confirmButtonEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        confirmButtonEvent = findViewById(R.id.confirmButtonEvent);

        confirmButtonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateEventActivity.this, FeedActivity.class));
            }
        });
    }
}