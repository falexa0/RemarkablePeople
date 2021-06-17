package com.example.rmptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static com.example.rmptest.MainActivity.userEntity;

public class ProfileActivity extends AppCompatActivity {



    Button feed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

 feed = findViewById(R.id.feed);


        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEntity = userEntity;
                String name = userEntity.getName();
                startActivity(new Intent(ProfileActivity.this, FeedActivity.class).putExtra("name", name));
            }
        });





    }
}
