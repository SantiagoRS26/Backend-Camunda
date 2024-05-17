package org.example.backendcamunda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    String camundaUrl = "http://localhost:8080/camunda";

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUserIdByName(String username) {
        URI uri = UriComponentsBuilder.fromHttpUrl(camundaUrl)
                .path("/user")
                .queryParam("name", username)
                .build()
                .toUri();

        String userId = restTemplate.getForObject(uri, String.class);
        return userId;
    }
}
