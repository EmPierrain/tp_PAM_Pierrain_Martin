package com.example.isicine.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.isicine.R;
import com.example.isicine.model.Film;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>  {
    private List<Film> filmList = new ArrayList<>();

    private OnMovieClickedListener listener;

    public MovieAdapter(OnMovieClickedListener clickListener){
        listener = clickListener;
    }

    public void addFilm(Film f){
        filmList.add(f);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Film film = filmList.get(position);
        holder.title.setText(filmList.get(position).onShow.movie.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicked(film);
            }
        });
        int hour = film.onShow.movie.runtime/3600;
        int minute = film.onShow.movie.runtime/60 - hour * 60;
        String duration = Integer.toString(hour) + "h" + Integer.toString(minute) + " | ";
        for (int i = 0; i<film.onShow.movie.genre.size(); ++i) {
            duration = duration + film.onShow.movie.genre.get(i).name;
            if(i<film.onShow.movie.genre.size()-1){
                duration += ", ";
            }
        }
        holder.duration.setText(duration);
        Picasso.get().load(film.onShow.movie.poster.href).into(holder.img);
        holder.pressRate.setIsIndicator(true);
        holder.pressRate.setRating(film.onShow.movie.statistics.pressRating);
        holder.publicRate.setIsIndicator(true);
        holder.publicRate.setRating(film.onShow.movie.statistics.userRating);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView title;
        ImageView img;
        TextView duration;
        RatingBar pressRate, publicRate;
        MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            img = view.findViewById(R.id.poster);
            duration = view.findViewById(R.id.duration);
            pressRate = view.findViewById(R.id.pressRate);
            publicRate = view.findViewById(R.id.publicRate);
        }

        @Override
        public void onClick(View v) {
            Log.d("MovieAdapter", "CLICK");
        }
    }
    public interface OnMovieClickedListener {
        void onMovieClicked(Film movie);
    }

}
