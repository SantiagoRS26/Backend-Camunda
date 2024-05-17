package org.example.backendcamunda.controllers;

import org.example.backendcamunda.models.ClientModel;
import org.example.backendcamunda.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    private final ProcessService processService;

    public ClientController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/informacionCliente")
    public String informacionCliente() {
        return "informacionCliente";
    }

    @PostMapping("/iniciarProceso")
    public ModelAndView startClientProcess(@ModelAttribute ClientModel clientModel) {
        processService.StartProcess(clientModel);
        return new ModelAndView("redirect:/");
    }
}
