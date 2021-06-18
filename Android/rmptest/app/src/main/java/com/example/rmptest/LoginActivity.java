
        package com.example.rmptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registerBtn = findViewById(R.id.registerBtn);

       //if user clicks on button criar conta then it will go to RegisterActivity

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


//login 
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    //only if peronal information is empty
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    //perform query
                    UserEntity user = AppDatabase.getDataBase(LoginActivity.this).getUserDao().getUserByLogin(email.getText().toString(), password.getText().toString());

                    if(user != null){
                        startActivity(new Intent(LoginActivity.this, FeedActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Credenciais erradas", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }


}