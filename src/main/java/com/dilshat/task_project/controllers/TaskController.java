package com.dilshat.task_project.controllers;

import com.dilshat.task_project.models.Task;
import com.dilshat.task_project.models.User;
import com.dilshat.task_project.services.impl.TaskService;
import com.dilshat.task_project.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String tasks(){
        return "redirect:/tasks";
    }


    //Данный маппинг возвращает все задачи которые имеются в БД
    @GetMapping("/tasks")
    public String tasks(
            Model model
    ){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }


    /*Данный маппинг возвращает список всех пользователей,для того чтобы можно было
     выбрать автора задачи*/
    @GetMapping("/tasks/new")
    public String newTask(
            Model model
    ){
        List<User> authors = userServiceImpl.getAllUsers();
        model.addAttribute("authors", authors);
        return "newTask";
    }

    //Маппинг принимает параметры с фронта, делаем проверку по автору задачи, далее сохраняем задачу в бд.
    @PostMapping("/tasks/new")
    public String newTask(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(name = "done", required = false, defaultValue = "false") boolean done,
            @RequestParam(name = "author_id") Long authorId
    ){
        User author = userServiceImpl.getUserById(authorId);
        if(author == null) {
            throw new IllegalArgumentException();
        }
        Task task = new Task(null, title, description, done, author);
        task = taskService.addTask(task);
        return "redirect:/tasks/" + task.getId();
    }

    //Данный маппинг возвращает задачу по id для редактирования
    @GetMapping("/tasks/edit/{task_id}")
    public String editTask(
            @PathVariable(name = "task_id") Long taskId,
            Model model
    ){
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);

        List<User> authors = userServiceImpl.getAllUsers();
        model.addAttribute("authors", authors);

        return "editTask";
    }

    //Данный маппинг принимает параметры для задачи, и обновляет поля, затем сохраняет в бд.
    @PostMapping("/task/edit")
    public String editTask(
            @RequestParam(name = "id") Long taskId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam (name = "done", required = false, defaultValue = "false") boolean done,
            @RequestParam(name = "new_author_id") Long newAuthorId
    ){
        User newAuthor = userServiceImpl.getUserById(newAuthorId);
        if(newAuthor == null) {
            throw new IllegalArgumentException();
        }
        Task task = taskService.getTaskById(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDone(done);
        task.setAuthor(newAuthor);
        taskService.saveTask(task);
        return "redirect:/tasks/" + taskId;
    }

    //Данный маппинг возвращает задачу по Id
    @GetMapping("/tasks/{taskId}")
    public String taskById(
            Model model,
            @PathVariable Long taskId
    ){
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        return "taskDetails";
    }

    //Данный маппинг удаляет задачу по Id
    @DeleteMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/tasks";
    }
}
