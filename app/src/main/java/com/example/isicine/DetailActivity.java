package com.example.isicine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isicine.model.Film;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;

public class DetailActivity extends AppCompatActivity {

    private Film movie;
    private ShareActionProvider shareAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        movie = (Film)i.getSerializableExtra("MOVIE");
        ImageView posterVideo = findViewById(R.id.posterVideo);
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
        ActionBar menu = getSupportActionBar();

        TextView director = findViewById(R.id.detailDirector);
        director.setText("Directeur: " + movie.onShow.movie.castingShort.directors);
        Picasso.get().load(movie.onShow.movie.poster.href).resize(1000,400).centerCrop().into(posterVideo);

        if(movie.onShow.movie.trailer == null || movie.onShow.movie.trailer.href == null){
            ImageButton playButton = findViewById(R.id.youtubePlayButton);
            ViewGroup layout = (ViewGroup) playButton.getParent();
            layout.removeView(playButton);
        }




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem share = menu.findItem(R.id.action_share);

        ActionProvider actionProvider = MenuItemCompat.getActionProvider(share);

        shareAction = (ShareActionProvider) actionProvider;

        String shareMessage = "REGARDE C'EST COOL ! Ce film à l'air super bien ! Il s'appelle \"" + movie.onShow.movie.title + "\".";
        if(movie.onShow.movie.trailer !=null && movie.onShow.movie.trailer.href != null)
            shareMessage = shareMessage + " Regarde le trailer ici: "+ movie.onShow.movie.trailer.href;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Regarde ce film !");
        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        setShareIntent(intent);
        return true;
    }

    private void setShareIntent(Intent shareIntent){
        if(shareAction!=null){
            shareAction.setShareIntent(shareIntent);
        }
    }

    public void playTrailer(View v){
        try{
            Intent intent;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.onShow.movie.trailer.href));
            startActivity(intent);
        }
        catch (NullPointerException e){

        }
    }

}
