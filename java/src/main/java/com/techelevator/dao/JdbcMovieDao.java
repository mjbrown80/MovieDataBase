package com.techelevator.dao;

import com.sun.source.tree.CompoundAssignmentTree;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Component
public class JdbcMovieDao implements MovieDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, movie_title, movie_release_date, movie_type, movie_genre, number_discs, movie_location, movie_poster FROM movies;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Movie movie = mapRowToMovie(results);
                movies.add(movie);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        String sql = "SELECT movie_id, movie_title, movie_release_date, movie_type, movie_genre, number_discs, movie_location, movie_poster FROM movies WHERE movie_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()){
                movie = mapRowToMovie(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movie;
    }
    @Override
    public Movie createMovie(Movie movie) {
        Movie newMovie = null;
        String insertMovieSql = "INSERT INTO movies (movie_title, movie_release_date, movie_type, movie_genre, number_discs, movie_location, movie_poster) VALUES (?,?,?,?,?,?,?) RETURNING movie_id";
        try {
            int newMovieId = jdbcTemplate.queryForObject(insertMovieSql, int.class, movie.getMovieTitle(), movie.getMovieReleaseDate(), movie.getMovieType(),movie.getMovieGenre(), movie.getNumberOfDiscs(),movie.getMovieLocation(), movie.getMoviePoster());
            newMovie = getMovieById(newMovieId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return newMovie;
    }




    private Movie mapRowToMovie(SqlRowSet rs){
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setMovieTitle(rs.getString("movie_title"));
        movie.setMovieReleaseDate(rs.getDate("movie_release_date"));
        movie.setMovieType(rs.getString("movie_type"));
        movie.setMovieGenre(rs.getString("movie_genre"));
        movie.setNumberOfDiscs(rs.getInt("number_discs"));
        movie.setMovieLocation(rs.getString("movie_location"));
        movie.setMoviePoster(rs.getString("movie_poster"));
        return movie;
    }

}
