package com.techelevator.services;

import com.techelevator.model.MovieDto;

import java.util.List;

public interface OmdbApiService {

    List<MovieDto> getMoviesByName(String searchString);
}
