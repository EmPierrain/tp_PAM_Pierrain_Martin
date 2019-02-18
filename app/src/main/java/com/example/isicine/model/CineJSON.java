package com.example.isicine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CineJSON implements Serializable {
    public Place place;
    public List<Film> movieShowtimes = new ArrayList<Film>();
}
