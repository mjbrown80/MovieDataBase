package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Movie;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

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
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add(@Valid @RequestBody MovieDao newMovie){
    }
}
