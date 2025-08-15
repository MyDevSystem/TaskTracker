package com.zakdevsystem.tasktracker.services.impl;

import com.zakdevsystem.tasktracker.domain.entities.Task;
import com.zakdevsystem.tasktracker.repositories.TaskRepository;
import com.zakdevsystem.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }
}
