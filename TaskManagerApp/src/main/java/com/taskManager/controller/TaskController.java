package com.taskManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.taskManager.dto.TaskDto;
import com.taskManager.service.TaskService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/tasks")
	public String getAllTasks(Model model) {
		List<TaskDto> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);
		
		return "task/tasklist";
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/tasks")
	public String saveTask(@ModelAttribute("task") TaskDto taskDto, Model model) {
//		Save Logic
		taskService.createTask(taskDto);
		
		return "redirect:/tasks?success";
	}
	

	@Transactional
	@GetMapping("/createTask")
	public String createTaskForm(Model model) {
		TaskDto taskDto = new TaskDto();
		model.addAttribute("task", taskDto);
		
		return "task/createTask";
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/tasks/delete/{id}")
	public String deleteTask(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasks/edit/{id}")
	public String editTask(@PathVariable ("id") Long id, Model model) {
		TaskDto taskDto = taskService.getTaskById(id);
		model.addAttribute("task", taskDto);
		return "task/editTask";
	}
	
	
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping("/tasks/{id}")
	public String updateTask(@PathVariable("id")Long id, @ModelAttribute("task") TaskDto taskDto) {
		taskDto.setId(id);
		taskService.updateTask(taskDto);
		return "redirect:/tasks?updated";
	}
	
}









