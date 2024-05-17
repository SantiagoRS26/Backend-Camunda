package org.example.backendcamunda.controllers;


import org.example.backendcamunda.controllers.models.InfoTasksDTO;
import org.example.backendcamunda.models.UserModel;
import org.example.backendcamunda.services.TaskService;
import org.example.backendcamunda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public EmployeeController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/listaTareas")
    public String listaTareas(Model model) {
        List<InfoTasksDTO> tasks = taskService.getAvailableTasks();
        model.addAttribute("tasks", tasks);
        return "listaTareas";
    }

    @PostMapping("/asignarTareaUsuario")
    public ModelAndView assignTask(@RequestParam("taskId") String taskId, @RequestParam("userId") String userId) {
        taskService.assignTask(taskId, userId);
        return new ModelAndView("redirect:/listaTareas");
    }

    @GetMapping("/asignarTarea")
    public String showAssignTasks(Model model) {
        List<InfoTasksDTO> tasks = taskService.getAvailableTasks();
        List<UserModel> users = userService.getAllUsers();
        model.addAttribute("tasks", tasks);
        model.addAttribute("users", users);
        return "asignarTarea";
    }

    @PostMapping("/reclamarTarea")
    public ModelAndView claimTask(@RequestParam("taskId") String taskId) {
        taskService.claimTask(taskId, "demo");
        System.out.println(taskId);
        return new ModelAndView("redirect:/listaTareas");
    }
}
