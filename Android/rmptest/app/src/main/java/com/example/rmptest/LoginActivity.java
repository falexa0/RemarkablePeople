package com.example.rmptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

        //if the user clicks on button saying that they don't have an account than it takes them to the
        //register activity

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    //only if peronal information is empty
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();//Mostra mensagem a dizer que é necessário preencher todos os campos
                } else {
                    //perform query
                    AppDatabase userDataBase = AppDatabase.getDataBase(getApplicationContext());
                    UserDao userDao = userDataBase.getUserDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(emailText, passwordText);
                            if(userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //only if personal information are wrong
                                        Toast.makeText(getApplicationContext(), "As credenciais estão erradas", Toast.LENGTH_SHORT).show();// Se a password e username estiverem errados mostra mensagem a dizer que os dados estão errados
                                    }
                                });
                            }else{
                                MainActivity.userEntity = userEntity;
                                MainActivity.userLoggedInId = userEntity.getId();
                                String name = userEntity.getName();
                                //when logged in sucessfully
                                startActivity(new Intent(LoginActivity.this, ProfileActivity.class).putExtra("name", name)); //Começa uma nova activity (Profile Activity) no perfil do usuário

                            }

                        }
                    }).start();
                }
            }
        });


    }


}