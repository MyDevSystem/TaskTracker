package com.zakdevsystem.tasktracker.services.impl;

import com.zakdevsystem.tasktracker.domain.entities.Task;
import com.zakdevsystem.tasktracker.domain.entities.TaskList;
import com.zakdevsystem.tasktracker.domain.entities.TaskPriority;
import com.zakdevsystem.tasktracker.domain.entities.TaskStatus;
import com.zakdevsystem.tasktracker.repositories.TaskListRepository;
import com.zakdevsystem.tasktracker.repositories.TaskRepository;
import com.zakdevsystem.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task list ID provided"));

        LocalDateTime timeNow = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                timeNow,
                timeNow,
                taskList
        );

        return taskRepository.save(taskToSave);
    }
}
