package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText mTextTitle;
    Button mButtonSearch;
    Button mButtonBack;
    DatabaseHelper2 myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mTextTitle = (EditText) findViewById(R.id.edittext_title);
        mButtonSearch = (Button) findViewById(R.id.button_search);
        mButtonBack = (Button)findViewById(R.id.button_back);
        myDb = new DatabaseHelper2(this);






        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTextTitle.getText().toString();
                SQLiteDatabase myDb = getApplicationContext().openOrCreateDatabase("books.db", Context.MODE_PRIVATE, null);
                Cursor c = myDb.rawQuery("select * from books_table where mTextTitle = '" + title + "'",new String[] {title});
                if (c.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Title \t" + c.getString(2) + "\n");
                    buffer.append("Author \t" + c.getString(3) + "\n");
                    buffer.append("Genre \t" + c.getString(4) + "\n");
                    buffer.append("Edition \t" + c.getString(5) + "\n");
                    buffer.append("ISBN \t" + c.getString(6) + "\n");

                }
                Toast.makeText(getApplicationContext(), "Result \n" + buffer.toString(), Toast.LENGTH_LONG).show();
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });
    }
}
