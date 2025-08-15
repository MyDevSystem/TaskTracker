package com.zakdevsystem.tasktracker.mapper;

import com.zakdevsystem.tasktracker.domain.dto.TaskListDto;
import com.zakdevsystem.tasktracker.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
