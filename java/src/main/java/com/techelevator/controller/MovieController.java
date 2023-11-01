package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieDao movieDao;
    private UserDao userDao;

    public MovieController(MovieDao movieDao, UserDao userDao) {
        this.movieDao = movieDao;
        this.userDao = userDao;
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



}
