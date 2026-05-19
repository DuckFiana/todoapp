package de.diana.todoapp.repository;

import de.diana.todoapp.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository für Datenbankzugriffe auf Task. */
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategoryId(Long categoryId);
}