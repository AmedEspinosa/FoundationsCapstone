package com.kenzie.app;

// This comes from the code in the reading
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomHttpClient {

    //TODO: Write sendGET method that takes URL and returns response

    //Standard GET request borrowed from the reading
    public String sendGET(String URLString) {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URLString);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {

                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (InterruptedException | IOException e) {
            return e.getMessage();
        }
    }

// Custom method to format my JSON file to only account for what my program needs
    // mirrored previous assignments and examples from the readings

    public String formatCluesDTO (String jsonString)throws JsonProcessingException{

        ObjectMapper objectMapper = new ObjectMapper();
        CluesDTO cluesDTO = objectMapper.readValue(jsonString,CluesDTO.class);

        StringBuilder cluesString = new StringBuilder();
        cluesString.append("Category: ")
                .append(cluesDTO.getCategory().getTitle())
                .append("\n")
                .append("Question: ")
                .append(cluesDTO.getQuestion());

        return cluesString.toString();
    }


    // Custom method to check if the user inputted response is equal to the correct answer
    public String answerCheck (String jsonString, String userAnswer)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CluesDTO cluesDTO = objectMapper.readValue(jsonString,CluesDTO.class);
        String correctAnswer = cluesDTO.getAnswer();

        StringBuilder sbCorrect = new StringBuilder("Congrats! Your Answer was correct!");

        StringBuilder sbIncorrect = new StringBuilder("Sorry! Your answer was incorrect!");
        sbIncorrect.append("\n")
                .append("The correct answer was: " + correctAnswer);

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            return sbCorrect.toString();
        } else return sbIncorrect.toString();
    }
}

