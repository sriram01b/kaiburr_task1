package com.task1.api.controller;

import com.task1.api.model.Task;
import com.task1.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repo;

    // GET: Retrieve all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    // GET: Retrieve a single task by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> task = repo.findById(id);
        // If the task exists, return 200 OK with the task object.
        // If not, return 404 Not Found.
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Find tasks by name (case-insensitive search)
    @GetMapping("/findByName/{name}")
    public List<Task> findTasksByName(@PathVariable String name) {
        return repo.findByNameContaining(name);
    }

    // PUT: Create a new task or update an existing one
    @PutMapping
    public ResponseEntity<Task> createOrUpdateTask(@RequestBody Task task) {
        // Security check as required by the PDF
        if (task.getCommand() != null && (task.getCommand().contains("rm") || task.getCommand().contains("sudo"))) {
            // Returning a 400 Bad Request status if the command is disallowed
            return ResponseEntity.badRequest().build();
        }
        Task savedTask = repo.save(task);
        return ResponseEntity.ok(savedTask);
    }

    // DELETE: Delete a task by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

