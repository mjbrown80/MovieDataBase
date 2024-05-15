package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.MovieDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class RestOmdbApiService implements OmdbApiService{
    @Value("${API_URL}")
    private String apiUrl;
    @Value("${API_KEY}")
    private String key;

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;


    @Override
    public List<MovieDto> getMoviesByName(String query) {

        String url = apiUrl + key + "&s=" + query;

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        List<MovieDto> movies = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());
        try {
            jsonNode = objectMapper.readTree((response.getBody()));
            JsonNode root = jsonNode.path("Search");

            for (int i = 0; i < root.size(); i++){
                String title =  root.path(i).path("Title").asText();
                int year = root.path(i).path("Year").asInt();
                String imdbId = root.path(i).path("imdbID").asText();
                String poster = root.path(i).path("Poster").asText();

                MovieDto movieDto = new MovieDto(title, year, imdbId, poster);
                movies.add(movieDto);
            }

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }
}
