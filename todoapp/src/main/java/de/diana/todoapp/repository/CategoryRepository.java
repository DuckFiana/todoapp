package de.diana.todoapp.repository;

import de.diana.todoapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository für Datenbankzugriffe auf Category. */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}