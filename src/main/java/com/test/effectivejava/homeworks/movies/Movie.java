package com.test.effectivejava.homeworks.movies;

import lombok.Builder;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Builder
public class Movie {

    private String name;
    private BigDecimal rating;

    public Movie(String name, BigDecimal rating){
        this.name = name;
        this.rating = rating;
    }

    public static Movie returnMovie(String name, String ratingString){
        return new Movie(name, BigDecimal.valueOf(Double.parseDouble(ratingString)));
    }

//    public static List<Movie> getMoviesFromFile(){
//
//    }
}
