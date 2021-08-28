package com.example.bookstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.nio.file.Paths;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
         EditText mTextTitle;
         EditText mAuthor;
         EditText mGenre;
         EditText mEdition;
         EditText mISBN;
         EditText mId;
         Button mButtonAdd;
         Button mButtonDelete;
         Button mButtonUpdate;
         Button mButtonView;
         Button mButtonSearch;
         DatabaseHelper2 myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        myDb = new DatabaseHelper2(this);
        mTextTitle = (EditText)findViewById(R.id.edittext_title);
        mAuthor= (EditText)findViewById(R.id.edittext_author);
        mGenre= (EditText)findViewById(R.id.edittext_genre);
        mEdition=(EditText)findViewById(R.id.edittext_edition);
        mISBN = (EditText)findViewById(R.id.edittext_ISBN);
        mId = (EditText)findViewById(R.id.edittext_id);
        mButtonAdd = (Button)findViewById(R.id.button_add);
        mButtonUpdate = (Button)findViewById(R.id.button_update);
        mButtonDelete = (Button)findViewById(R.id.button_delete);
        mButtonView = (Button)findViewById(R.id.button_view_all);
        mButtonSearch = (Button)findViewById(R.id.button_search);

        ButtonAdd();
        viewAll();
        UpdateData();
        DeleteData();

    }


    public void DeleteData(){
        mButtonDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = myDb.deleteData(mId.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(MainScreen.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainScreen.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void UpdateData(){
        mButtonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            boolean isUpdate = myDb.updateData(mId.getText().toString(),mTextTitle.getText().toString(), mAuthor.getText().toString(), mGenre.getText().toString(),
                                    mEdition.getText().toString(), mISBN.getText().toString());
                            if (isUpdate == true)
                                Toast.makeText(MainScreen.this, "Data Updated", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainScreen.this, "Data not Updated", Toast.LENGTH_LONG).show();



                    }
                }
        );
    }

    public  void ButtonAdd() {
       mButtonAdd.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                     boolean isInserted =  myDb.insertData(mTextTitle.getText().toString(), mAuthor.getText().toString(), mGenre.getText().toString(),
                               mEdition.getText().toString(), mISBN.getText().toString());

                     if (isInserted = true)
                         Toast.makeText(MainScreen.this, "Data Inserted", Toast.LENGTH_LONG).show();
                     else
                         Toast.makeText(MainScreen.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                   }
               }
       );
    }

    public void viewAll() {
        mButtonView.setOnClickListener(
                new View.OnClickListener(){
                    public  void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Title :" + res.getString(1) + "\n");
                            buffer.append("Author :" + res.getString(2) + "\n");
                            buffer.append("Genre :" + res.getString(3) + "\n");
                            buffer.append("Edition :" + res.getString(4) + "\n");
                            buffer.append("ISBN :" + res.getString(5) + "\n\n");


                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        }
        public  void showMessage(String title, String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
    }


}