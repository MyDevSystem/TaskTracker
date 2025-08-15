package com.zakdevsystem.tasktracker.mapper.impl;

import com.zakdevsystem.tasktracker.domain.dto.TaskListDto;
import com.zakdevsystem.tasktracker.domain.entities.Task;
import com.zakdevsystem.tasktracker.domain.entities.TaskList;
import com.zakdevsystem.tasktracker.domain.entities.TaskStatus;
import com.zakdevsystem.tasktracker.mapper.TaskListMapper;
import com.zakdevsystem.tasktracker.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList()).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if (null == tasks) return null;

        long closedTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSE == task.getStatus())
                .count();

        return (double) closedTaskCount / tasks.size();
    }
}
