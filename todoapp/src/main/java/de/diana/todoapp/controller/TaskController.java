package de.diana.todoapp.controller;

import de.diana.todoapp.model.Task;
import de.diana.todoapp.service.CategoryService;
import de.diana.todoapp.service.TaskService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Controller für die Verwaltung von Aufgaben. */
@Controller
@RequestMapping("/aufgaben")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final CategoryService categoryService;

    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String alleAufgaben(Model model) {
        model.addAttribute("aufgaben", taskService.alleAufgaben());
        model.addAttribute("kategorien", categoryService.alleKategorien());
        model.addAttribute("neueAufgabe", new Task());
        return "aufgaben";
    }

    @PostMapping("/speichern")
    public String speichern(@ModelAttribute Task task) {
        taskService.speichern(task);
        return "redirect:/aufgaben";
    }

    @GetMapping("/bearbeiten/{id}")
    public String bearbeitenForm(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.findeNachId(id);
        if (task.isEmpty()) {
            logger.warn("Aufgabe mit ID {} nicht gefunden.", id);
            return "redirect:/aufgaben";
        }
        model.addAttribute("aufgabe", task.get());
        model.addAttribute("kategorien", categoryService.alleKategorien());
        return "aufgabe-bearbeiten";
    }

    @PostMapping("/aktualisieren")
    public String aktualisieren(@ModelAttribute Task task) {
        taskService.aktualisieren(task);
        return "redirect:/aufgaben";
    }

    @PostMapping("/loeschen/{id}")
    public String loeschen(@PathVariable Long id) {
        taskService.loeschen(id);
        return "redirect:/aufgaben";
    }
}