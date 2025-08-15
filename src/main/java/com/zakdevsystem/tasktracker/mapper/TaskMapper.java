package com.zakdevsystem.tasktracker.mapper;

import com.zakdevsystem.tasktracker.domain.dto.TaskDto;
import com.zakdevsystem.tasktracker.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
