package com.example.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton add_button;
    MyDatabase myDB;
    List<BlogItem> listBlogItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_button = findViewById(R.id.add_button);
        listView = findViewById(R.id.listView);

        myDB = new MyDatabase(MainActivity.this);

        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        listBlogItem = saveDataInArrays();
        BlogItemAdapter adapter = new BlogItemAdapter(MainActivity.this, listBlogItem);
        listView.setAdapter(adapter);
    }

    private List<BlogItem> saveDataInArrays() {
        List<BlogItem> listBlogItem = new ArrayList<>();

        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Pas de Blogs !!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String auteur = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_AUTHOR));
                @SuppressLint("Range") String title  = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_TITLE));
                @SuppressLint("Range") String text   = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_TEXT));
                @SuppressLint("Range") String date   = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_DATE));
                listBlogItem.add(new BlogItem(auteur,title, text,date));
            }
        }

        cursor.close();
        return listBlogItem;
    }
}
