package de.diana.todoapp.service;

import de.diana.todoapp.model.Category;
import de.diana.todoapp.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** Service für die Verwaltung von Kategorien. */
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> alleKategorien() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findeNachId(Long id) {
        return categoryRepository.findById(id);
    }

    public void speichern(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            logger.warn("Kategorie mit Name '{}' existiert bereits.", category.getName());
            return;
        }
        categoryRepository.save(category);
    }

    public void aktualisieren(Category category) {
        categoryRepository.save(category);
    }

    public void loeschen(Long id) {
        if (!categoryRepository.existsById(id)) {
            logger.warn("Kategorie mit ID {} nicht gefunden.", id);
            return;
        }
        categoryRepository.deleteById(id);
    }
}