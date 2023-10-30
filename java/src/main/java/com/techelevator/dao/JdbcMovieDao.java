package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMovieDao implements MovieDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, movie_title, movie_release_date, movie_type, movie_genre, number_of_discs, movie_location FROM movies;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next());
            Movie movie = mapRowToMovie(results);
            movies.add(movie);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movies;
    }

    private Movie mapRowToMovie(SqlRowSet rs){
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setMovieTitle(rs.getString("movie_title"));
        movie.setMovieReleaseDate(rs.getDate("movie_release_date"));
        movie.setMovieType(rs.getString("movie_type"));
        movie.setMovieGenre(rs.getString("movie_genre"));
        movie.setNumberOfDiscs(rs.getInt("number_disc"));
        movie.setMovieLocation(rs.getString("movie_location"));
        return movie;
    }

}
