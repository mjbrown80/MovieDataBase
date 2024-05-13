package com.techelevator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestOmdbApiService implements OmdbApiService{
    @Value("${API_URL}")
    private String apiUrl;
    @Value("${API_KEY}")
    private String key;

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;


    @Override
    public List<Movie> getMoviesByName(String searchString) {

        String url = apiUrl + key + "&s=" + searchString;

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        List<Movie> movies = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());


        return movies;
    }
}
