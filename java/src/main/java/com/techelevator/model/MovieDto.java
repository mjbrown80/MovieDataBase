package com.techelevator.model;

public class MovieDto {
    private String title;
    private int year;
    private int imdbId;
    private String poster;

    public MovieDto(String title, int year, int imdbId, String poster) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImdbId() {
        return imdbId;
    }

    public void setImdbId(int imdbId) {
        this.imdbId = imdbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", imdbId=" + imdbId +
                ", poster='" + poster + '\'' +
                '}';
    }
}
