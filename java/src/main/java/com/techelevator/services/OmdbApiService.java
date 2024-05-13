package com.techelevator.services;

import com.techelevator.model.Movie;

import java.util.List;

public interface OmdbApiService {

    List<Movie> getMoviesByName(String searchString);
}
