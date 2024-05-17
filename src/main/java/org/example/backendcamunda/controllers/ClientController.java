package org.example.backendcamunda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController {

    @GetMapping("/informacionCliente")
    public String informacionCliente() {
        return "informacionCliente";
    }
}
