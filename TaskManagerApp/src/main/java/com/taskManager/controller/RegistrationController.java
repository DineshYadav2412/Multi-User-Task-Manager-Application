//package com.taskManager.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.taskManager.service.TaskService;
//
//import lombok.Data;
//
//@Controller
//@Data
//public class RegistrationController {
//
//	@Autowired
//	private TaskService taskService;
//	
//	 @GetMapping("/register")
//	    public String showRegistrationForm(Model model) {
//	        model.addAttribute("user", new User());
//	        return "task/register"; // returns the registration.html page
//	    }
//
//	    // Handling the registration form submission
////	    @PostMapping("/register")
////	    public String registerUser(@ModelAttribute("user") User user, Model model) {
////	        userService.save(user); // Save the user via the service layer
////	        model.addAttribute("message", "User registered successfully!");
////	        return "login"; // Redirect or return a success page
////	    }
//}
