package de.diana.todoapp.controller;

import de.diana.todoapp.model.Category;
import de.diana.todoapp.service.CategoryService;
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

/** Controller für die Verwaltung von Kategorien. */
@Controller
@RequestMapping("/kategorien")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String alleKategorien(Model model) {
        model.addAttribute("kategorien", categoryService.alleKategorien());
        model.addAttribute("neueKategorie", new Category());
        return "kategorien";
    }

    @PostMapping("/speichern")
    public String speichern(@ModelAttribute Category category) {
        categoryService.speichern(category);
        return "redirect:/kategorien";
    }

    @GetMapping("/bearbeiten/{id}")
    public String bearbeitenForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findeNachId(id);
        if (category.isEmpty()) {
            logger.warn("Kategorie mit ID {} nicht gefunden.", id);
            return "redirect:/kategorien";
        }
        model.addAttribute("kategorie", category.get());
        return "kategorie-bearbeiten";
    }

    @PostMapping("/aktualisieren")
    public String aktualisieren(@ModelAttribute Category category) {
        categoryService.aktualisieren(category);
        return "redirect:/kategorien";
    }

    @PostMapping("/loeschen/{id}")
    public String loeschen(@PathVariable Long id) {
        categoryService.loeschen(id);
        return "redirect:/kategorien";
    }
}