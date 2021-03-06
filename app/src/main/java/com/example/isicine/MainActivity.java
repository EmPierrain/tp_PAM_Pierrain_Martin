package com.example.isicine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.isicine.adapter.MovieAdapter;
import com.example.isicine.model.CineJSON;
import com.example.isicine.model.Film;
import com.example.isicine.rest.ApiHelper;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickedListener {

    private RecyclerView listeFilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.default_title);
        listeFilms = findViewById(R.id.ListeFilms);
        listeFilms.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final MovieAdapter movieAdapter = new MovieAdapter(this);
        listeFilms.setAdapter(movieAdapter);
        //listeFilms.setLayoutManager();

        ApiHelper json = ApiHelper.getInstance();
        json.getCineAPI().getCine().enqueue(new Callback<CineJSON>() {
            @Override
            public void onResponse(Call<CineJSON> call, Response<CineJSON> response) {
                CineJSON json = response.body();

                for(Film film:json.movieShowtimes){
                    movieAdapter.addFilm(film);
                    movieAdapter.notifyItemInserted(movieAdapter.getItemCount());
                }


            }

            @Override
            public void onFailure(Call<CineJSON> call, Throwable t) {

            }

        });
    }

    @Override
    public void onMovieClicked(Film movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);
    }
}
