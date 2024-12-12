package com.taskManager.service;

import java.util.List;

import com.taskManager.dto.UserDto;
import com.taskManager.entity.User;

public interface UserService {

	void saveUser(UserDto userDto);

	User findUserByEmail(String email);
	
	List<UserDto> findAllUsers(); 
}
