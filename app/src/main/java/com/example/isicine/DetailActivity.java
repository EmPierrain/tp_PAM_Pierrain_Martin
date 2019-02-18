package com.example.isicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.isicine.model.Film;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        Film movie = (Film)i.getSerializableExtra("MOVIE");
        TextView durationTextView = findViewById(R.id.detailDuration);


        int hour = movie.onShow.movie.runtime/3600;
        int minute = movie.onShow.movie.runtime/60 - hour * 60;
        String duration = Integer.toString(hour) + "h" + Integer.toString(minute) + " | ";
        for (int j = 0; j<movie.onShow.movie.genre.size(); ++j) {
            duration = duration + movie.onShow.movie.genre.get(j).name;
            if(j<movie.onShow.movie.genre.size()-1){
                duration += ", ";
            }
        }
        durationTextView.setText(duration);
        Objects.requireNonNull(getSupportActionBar()).setTitle(movie.onShow.movie.title);


        TextView director = findViewById(R.id.detailDirector);
        director.setText("Directeur: " + movie.onShow.movie.castingShort.directors);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
