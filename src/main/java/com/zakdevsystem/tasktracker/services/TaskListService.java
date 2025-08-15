package com.zakdevsystem.tasktracker.services;

import com.zakdevsystem.tasktracker.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskList> listTaskLists();

}
