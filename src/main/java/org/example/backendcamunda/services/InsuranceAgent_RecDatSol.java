package org.example.backendcamunda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InsuranceAgent_RecDatSol {
    private final RestTemplate restTemplate;
    private final String camundaUrl = "http://localhost:8080/engine-rest";

    @Autowired
    public InsuranceAgent_RecDatSol(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
