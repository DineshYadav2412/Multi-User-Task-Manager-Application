package com.taskManager.service;

import java.util.List;

import com.taskManager.dto.TaskDto;
import com.taskManager.repository.TaskRepository;

public interface TaskService {

	
	List<TaskDto> getAllTasks();
	
	void createTask(TaskDto taskDto);
	
	TaskDto getTaskById(Long id);
	
	void updateTask(TaskDto taskDto);
	
	void deleteTask(Long id);
}
