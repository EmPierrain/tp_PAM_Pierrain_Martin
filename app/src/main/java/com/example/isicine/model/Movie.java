package com.example.isicine.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{
    public String title;
    public CastingShort castingShort;
    public Poster poster;
    public int runtime;
    public ArrayList<Genre> genre;
    public Statistics statistics;
}

