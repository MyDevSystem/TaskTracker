package com.zakdevsystem.tasktracker.services.impl;

import com.zakdevsystem.tasktracker.domain.entities.TaskList;
import com.zakdevsystem.tasktracker.repositories.TaskListRepository;
import com.zakdevsystem.tasktracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }
}
