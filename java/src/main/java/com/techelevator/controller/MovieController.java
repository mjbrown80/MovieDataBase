package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieDto;
import com.techelevator.model.User;
import com.techelevator.services.OmdbApiService;
import com.techelevator.services.RestOmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated")
public class MovieController {
    @Autowired
    private MovieDao movieDao;
    private UserDao userDao;
    @Autowired
    private OmdbApiService omdbApiService;

    public MovieController(MovieDao movieDao, UserDao userDao, OmdbApiService omdbApiService) {
        this.movieDao = movieDao;
        this.userDao = userDao;
        this.omdbApiService = omdbApiService;

    }
    @RequestMapping(path ="/movies", method = RequestMethod.GET)
    public List<Movie> movies(Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        return movieDao.getMovies();
    }

    @RequestMapping(path ="/movies/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        Movie movie = movieDao.getMovieById(id);
        return movie;
    }

    @RequestMapping(path = "/movies/title/{title}", method = RequestMethod.GET)
    public Movie getMovieByTitle(@PathVariable String title, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        Movie movie = movieDao.getMovieByTitle(title);
        return movie;
    }

    @RequestMapping(path = "/movies/genre/{genre}", method = RequestMethod.GET)
    public Movie getMoviesByGenre(@PathVariable String genre, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        Movie movie = movieDao.getMovieByGenre(genre);
        return movie;
    }

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movie> add(@Valid @RequestBody Movie newMovie) {
        try {
            Movie createdMovie = movieDao.createMovie(newMovie);
            return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
        } catch (DaoException e) {
            Movie errorMovie = new Movie();
            errorMovie.setMovieTitle("Error");
            errorMovie.setMovieGenre("Error");
            return new ResponseEntity<>(errorMovie, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path="/movieapi", method = RequestMethod.GET)
    public List<MovieDto> movieSearch(@RequestParam String query){
        return omdbApiService.getMoviesByName(query);
    }



}
