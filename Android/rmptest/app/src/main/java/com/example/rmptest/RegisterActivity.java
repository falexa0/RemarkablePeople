package com.example.rmptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText password, name, confirmarPassword, email, phoneNumber;
    Button register;
    Button login;


    public static long userLoggedInId;
    public static UserEntity userEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.password);
        confirmarPassword = findViewById(R.id.confirmarPassword);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);





        //button goes back to Login Activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


//register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create user

                List<UserEntity> userList = AppDatabase.getDataBase(RegisterActivity.this).getUserDao().
                        getUsersByEmail(email.getText().toString(), name.getText().toString());

                if (userList.size() == 0) {
                    //Insert personal information from user
                    UserEntity userEntity = new UserEntity();
                    userEntity.setPassword(password.getText().toString());
                    userEntity.setEmail(email.getText().toString());
                    userEntity.setName(name.getText().toString());
                    userEntity.setPhoneNumber(phoneNumber.getText().toString());

                    AppDatabase.getDataBase(RegisterActivity.this).getUserDao().registerUser(userEntity);

                } else {
                    //If user doesn't put their information it will display a text saying that they need to insert their
                    //personal information
                    Toast.makeText(getApplicationContext(), "User ja existe!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}