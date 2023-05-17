package com.example.blog;
import java.time.LocalDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {
    EditText auteur_input ,titre_input,contenue_input;

    Button add_blog_button;
    FloatingActionButton close_button;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        auteur_input = findViewById(R.id.auteur_input);
        titre_input = findViewById(R.id.titre_input);
        contenue_input = findViewById(R.id.contenue_input);
        add_blog_button = findViewById(R.id.add_blog_button);
        LocalDate dateBlog= LocalDate.now();
        String DateString = dateBlog.toString();

        add_blog_button.setOnClickListener(view -> {
            MyDatabase myDB =new MyDatabase(AddActivity.this);
            myDB.addBlog(auteur_input.getText().toString().trim(),
                    titre_input.getText().toString().trim(),
                    contenue_input.getText().toString().trim(),
                    DateString);
        });
        close_button = findViewById(R.id.close_button);
        close_button.setOnClickListener(view -> {
            Intent intent = new Intent( this,MainActivity.class);
            startActivity(intent);
        });
    }
}