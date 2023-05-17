package com.example.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class BlogDetailsActivity extends AppCompatActivity {
    TextView auteurTextView, titreTextView, textTextView, dateTextView;
    FloatingActionButton back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);

        back_button = findViewById(R.id.ic_back);
        back_button.setOnClickListener(view -> {
            Intent intent = new Intent( this,MainActivity.class);
            startActivity(intent);
        });


        auteurTextView = findViewById(R.id.auteurTextView);
        titreTextView = findViewById(R.id.titreTextView);
        textTextView = findViewById(R.id.textTextView2);
        dateTextView = findViewById(R.id.dateTextView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String auteur = extras.getString("auteur");
            String titre = extras.getString("titre");
            String text = extras.getString("text");
            String date = extras.getString("date");

            auteurTextView.setText(auteur);
            titreTextView.setText(titre);
            textTextView.setText(text);
            dateTextView.setText(date);
        }
    }
}
