package com.zakdevsystem.tasktracker.controllers;

import com.zakdevsystem.tasktracker.domain.dto.TaskDto;
import com.zakdevsystem.tasktracker.domain.entities.Task;
import com.zakdevsystem.tasktracker.mapper.TaskMapper;
import com.zakdevsystem.tasktracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id")UUID taskListId){
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto){

        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));

        return taskMapper.toDto(createdTask);

    }
}
