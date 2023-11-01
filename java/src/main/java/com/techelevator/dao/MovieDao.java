package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getMovies();
    Movie createMovie(Movie movie);
    Movie getMovieById(int id);
    Movie getMovieByTitle(String title);
    Movie getMovieByGenre(String genre);
}
