package com.dilshat.task_project.services.impl;

import com.dilshat.task_project.exceptions.TaskNotFoundException;
import com.dilshat.task_project.models.Task;
import com.dilshat.task_project.models.User;
import com.dilshat.task_project.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(new Task(0L, "NO TITLE", "NO DESCRIPTION", false,new User()));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId) {
        //Проверка, существует ли задача с заданным ID
        if (taskRepository.existsById(taskId)) {
            //если задача существует, удаляем ее
            taskRepository.deleteById(taskId);
        } else {
            try {
                throw new TaskNotFoundException("Task with ID " + taskId + " not found");
            } catch (TaskNotFoundException e) {
                e.printStackTrace();
            }
        }
    }}
