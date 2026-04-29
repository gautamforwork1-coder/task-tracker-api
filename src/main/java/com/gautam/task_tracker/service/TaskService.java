package com.gautam.task_tracker.service;

import com.gautam.task_tracker.entity.Task;
import com.gautam.task_tracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task>  getAllTasks(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()->new RuntimeException("Exception"+id));
    }
    public List<Task> getTaskByStatus(Task.Status status){
        return taskRepository.findByStatus(status);
    }

    public List<Task> searchTasksBytTitle(String keyword){
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Task createTask(Task task){
        if (task.getStatus()==null){
            task.setStatus(Task.Status.PENDING);
        }
        return taskRepository.save(task);
    }

    public Task updateTask(Long id,Task task){
        Task existing = getTaskById(id);
        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setStatus(task.getStatus());
        existing.setDueDate(task.getDueDate());
        return  taskRepository.save(existing);
    }

    public Task markAsCompleted(Long id){
        Task existing = getTaskById(id);
        existing.setStatus(Task.Status.COMPLETED);
        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }


}
