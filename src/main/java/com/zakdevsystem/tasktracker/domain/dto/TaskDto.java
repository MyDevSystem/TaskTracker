package com.zakdevsystem.tasktracker.domain.dto;

import com.zakdevsystem.tasktracker.domain.entities.TaskPriority;
import com.zakdevsystem.tasktracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
