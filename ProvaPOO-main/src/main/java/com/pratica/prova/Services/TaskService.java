package com.pratica.prova.Service;

import com.pratica.prova.Model.Task;
import com.pratica.prova.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus("A Fazer");
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        switch (task.getStatus()) {
            case "A Fazer":
                task.setStatus("Em Progresso");
                break;
            case "Em Progresso":
                task.setStatus("Concluído");
                break;
            default:
                throw new IllegalStateException("Tarefa já está concluída");
        }
        return taskRepository.save(task);
    }
}
