package de.diana.todoapp.service;

import de.diana.todoapp.model.Task;
import de.diana.todoapp.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** Service für die Verwaltung von Aufgaben. */
@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> alleAufgaben() {
        return taskRepository.findAll();
    }

    public List<Task> aufgabenNachKategorie(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    public Optional<Task> findeNachId(Long id) {
        return taskRepository.findById(id);
    }

    public void speichern(Task task) {
        taskRepository.save(task);
    }

    public void aktualisieren(Task task) {
        taskRepository.save(task);
    }

    public void loeschen(Long id) {
        if (!taskRepository.existsById(id)) {
            logger.warn("Aufgabe mit ID {} nicht gefunden.", id);
            return;
        }
        taskRepository.deleteById(id);
    }
}