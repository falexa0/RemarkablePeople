package com.example.rmptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText userId, password, name, confirmarPassword, email, phoneNumber;
    Button register;
    Button login;
    TextView isVolunteerText;
    public static long userLoggedInId;
    public static UserEntity userEntity;
    public static CheckBox isVolunteer;
    TextView emailText;
    TextView phoneText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmarPassword = findViewById(R.id.confirmarPassword);
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        isVolunteer = findViewById(R.id.isVolunteer);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        isVolunteerText = findViewById(R.id.isVolunteerText) ;
        emailText = findViewById(R.id.emailText);
        phoneText = findViewById(R.id.phoneText);


//button goes back to Login Activity
       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, LoginActivity.class));
           }
       });

       //if checkbox is checked the data will display on the text view (ProfileActivity)

      login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (isVolunteer.isChecked()){
                    isVolunteerText.setText("Voluntário");
                }

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create user
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());
                //userEntity.setEmail();

                if (validateInput((userEntity))) {
                    //Insert personal information from user
                    AppDatabase userDataBase = AppDatabase.getDataBase(getApplicationContext());
                    UserDao userDao = userDataBase.getUserDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //User registered
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //if the user puts their information then it displays a message saying that the user  is
                                    //register
                                    Toast.makeText(getApplicationContext(), "Utilizador Registado", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                }
                            });
                        }
                    }).start();
                } else {
                    //If user doesn't put their information it will display a text saying that they need to insert their
                    //personal information
                    Toast.makeText(getApplicationContext(), "Preencher todos os campos!", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


//error if personal information are empty
    private Boolean validateInput(UserEntity userEntity) {
        return !userEntity.getName().isEmpty() && !userEntity.getPassword().isEmpty() && !userEntity.getName().isEmpty() && !userEntity.getEmail().isEmpty();
    }

    //keeps going to login page if it user puts their personal information correctly

    private void login (){
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);

    }


}