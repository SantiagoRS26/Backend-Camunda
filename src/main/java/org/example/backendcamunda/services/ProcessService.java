package org.example.backendcamunda.services;

import org.example.backendcamunda.models.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProcessService {
    private final RestTemplate restTemplate;
    String camundaUrl = "http://localhost:8080/camunda";

    @Autowired
    public ProcessService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String StartProcess(String processDefinitionKey, ClientModel client) {
        URI uri = UriComponentsBuilder.fromHttpUrl(camundaUrl)
                .path("/process-definition/key/{key}/start")
                .buildAndExpand(processDefinitionKey)
                .toUri();

        Map<String, Object> variables = convertClientToVariables(client);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("variables", variables);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestBody, String.class);

        return response.getBody();
    }

    private Map<String, Object> convertClientToVariables(ClientModel client) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("nombreCliente", createVariableMap(client.getClientName()));
        variables.put("edadCliente", createVariableMap(client.getClientAge()));
        variables.put("marcaVehiculo", createVariableMap(client.getBrandVehicle()));
        variables.put("referenciaVehiculo", createVariableMap(client.getVehicleReference()));
        variables.put("cantSiniestros", createVariableMap(client.getNumberAccidents()));
        return variables;
    }

    private Map<String, Object> createVariableMap(Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put("value", value);
        return map;
    }
}
