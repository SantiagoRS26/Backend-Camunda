package org.example.backendcamunda.services;

import org.example.backendcamunda.controllers.models.InfoTasksDTO;
import org.example.backendcamunda.models.TaskModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final RestTemplate restTemplate;
    String camundaUrl = "http://localhost:8080/engine-rest";

    public TaskService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void claimTask(String taskId, String userId) {
        String url = camundaUrl + "/task/" + taskId + "/claim";
        Map<String, String> body = new HashMap<>();
        body.put("userId", userId);

        restTemplate.postForObject(url, body, Void.class);
    }

    public void completeTask(String taskId, Map<String, Object> variables) {
        String url = camundaUrl + "/task/" + taskId + "/complete";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("variables", variables);
        restTemplate.postForObject(url, requestBody, Void.class);
    }

    public List<InfoTasksDTO> getAvailableTasks() {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(camundaUrl)
                    .path("/task")
                    .queryParam("unassigned", true)
                    .toUriString();

            ResponseEntity<List<TaskModel>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TaskModel>>() {}
            );

            return response.getBody().stream()
                    .map(task -> new InfoTasksDTO(
                            task.getId(),
                            task.getName(),
                            task.getDescription(),
                            task.getCreated() != null ? task.getCreated().toString() : "No date provided"
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error recibiendo las tareas: " + e.getMessage());
            return null;
        }
    }
}
