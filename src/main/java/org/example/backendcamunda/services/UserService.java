package org.example.backendcamunda.services;

import org.example.backendcamunda.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    String camundaUrl = "http://localhost:8080/engine-rest";

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

    public List<UserModel> getAllUsers() {
        URI uri = UriComponentsBuilder.fromHttpUrl(camundaUrl)
                .path("/user")
                .build()
                .toUri();

        UserModel[] userArray = restTemplate.getForObject(uri, UserModel[].class);
        return Arrays.asList(userArray);
    }
}
