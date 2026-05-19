package de.diana.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Controller für die Startseite. */
@Controller
public class HomeController {

    @GetMapping("/")
    public String startseite() {
        return "index";
    }
}