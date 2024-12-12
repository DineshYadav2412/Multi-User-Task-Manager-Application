package com.taskManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.taskManager.dto.TaskDto;
import com.taskManager.entity.Task;
import com.taskManager.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service

public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public List<TaskDto> getAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return tasks.stream().map((task) -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void createTask(TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);
		taskRepository.save(task);

	}

	@Override
	public TaskDto getTaskById(Long id) {
	Task task = taskRepository.findById(id).get();
	TaskDto taskDto = modelMapper.map(task, TaskDto.class);
		return taskDto;
	}

	@Override
	public void updateTask(TaskDto taskDto) {
		taskRepository.save(modelMapper.map(taskDto, Task.class));

	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
		
	}

}
