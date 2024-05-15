package com.techelevator.model;

import java.util.Date;

public class Movie {
    private int movieId;
    private String imdbId;
    private String movieTitle;
    private Date movieReleaseDate;
    private String movieType;
    private String movieGenre;
    private int numberOfDiscs;
    private String movieLocation;
    private String moviePoster;

    public Movie(int movieId, String imdbId, String movieTitle, Date movieReleaseDate, String movieType, String movieGenre, int numberOfDiscs, String movieLocation, String moviePoster) {
        this.movieId = movieId;
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.movieReleaseDate = movieReleaseDate;
        this.movieType = movieType;
        this.movieGenre = movieGenre;
        this.numberOfDiscs = numberOfDiscs;
        this.movieLocation = movieLocation;
        this.moviePoster = moviePoster;
    }

    public Movie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Date getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(Date movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public int getNumberOfDiscs() {
        return numberOfDiscs;
    }

    public void setNumberOfDiscs(int numberOfDiscs) {
        this.numberOfDiscs = numberOfDiscs;
    }

    public String getMovieLocation() {
        return movieLocation;
    }

    public void setMovieLocation(String movieLocation) {
        this.movieLocation = movieLocation;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieReleaseDate=" + movieReleaseDate +
                ", movieType='" + movieType + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", numberOfDiscs=" + numberOfDiscs +
                ", movieLocation='" + movieLocation + '\'' +
                ", moviePoster='" + moviePoster + '\'' +
                '}';
    }
}

