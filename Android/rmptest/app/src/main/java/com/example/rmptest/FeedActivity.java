package com.example.rmptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;


public class FeedActivity extends AppCompatActivity {
    private TextView fName;
    private RecyclerView recyclerView;
    private Button profileUser;
    private EditText editTextPost;
    private CardAdapter adapter;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        profileUser = findViewById(R.id.profileUser);
        recyclerView = findViewById(R.id.postList);

        this.adapter = new CardAdapter(this);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.editTextPost = findViewById(R.id.editTextPost);

        fName = findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        fName.setText(name);


      profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, ProfileActivity.class));
            }
        });

    }
    //show posts
    public void post(View view) {
        String postText = this.editTextPost.getText().toString();

        PostsEntity newPost = new PostsEntity(0, RegisterActivity.userLoggedInId, new Date().getTime(), postText, "");
        AppDatabase.getDataBase(this).getPostsEntityDao().add(newPost);

        this.editTextPost.setText("");

        this.updateFeed();


    }

    //update posts
    private void updateFeed() {
        this.adapter.updateList(AppDatabase.getDataBase(this).getPostsEntityDao().getAll());
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.updateFeed();
    }


}





