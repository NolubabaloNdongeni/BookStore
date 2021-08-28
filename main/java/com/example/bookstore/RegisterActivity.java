package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
     EditText name, surname, username, email, phone_number, password;
     Button register, cancel;
     DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        databaseHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = name.getText().toString();
                String surnameValue = surname.getText().toString();
                String usernameValue = username.getText().toString();
                String emailValue = email.getText().toString();
                String phone_numberValue = phone_number.getText().toString();
                String passwordValue = password.getText().toString();

                if (nameValue.length() > 1){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", nameValue);
                    contentValues.put("surname", surnameValue);
                    contentValues.put("username", usernameValue);
                    contentValues.put("email", nameValue);
                    contentValues.put("phone_number", phone_numberValue);
                    contentValues.put("password", passwordValue);

                    databaseHelper.insertUser(contentValues);
                    Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Enter the values", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}