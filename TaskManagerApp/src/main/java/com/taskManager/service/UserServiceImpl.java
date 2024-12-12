package com.taskManager.service;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskManager.dto.UserDto;
import com.taskManager.entity.Role;
import com.taskManager.entity.User;
import com.taskManager.repository.RoleRepository;
import com.taskManager.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;

	
//	Constructor Dependency
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public void saveUser(UserDto userDto) {
	
		User user = new User();
		user.setName(userDto.getFirstName()+" "+userDto.getLastName());
		user.setEmail(userDto.getEmail());
//		Encrypted Password using spring security
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Role role = roleRepository.findByName("ROLE_USER");
		if (role==null) {
			role = checkRoleExist();
		}
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}
	
	@Override
	public User findUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
	
	
	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_USER");
		return roleRepository.save(role);
	}


	@Override
	public List<UserDto> findAllUsers() {
	List<User> users = userRepository.findAll();
		return users.stream().map((user)->mapToUserDto(user)).collect(Collectors.toList());
	}


	private UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto();
		String[] str = user.getName().split(" ");
		userDto.setFirstName(str[0]);
		userDto.setLastName(str[1]);
		userDto.setEmail(user.getEmail());
		return userDto;
		
//		UserDto userDto = new UserDto();
//	    String[] str = user.getName().split(" ");
//	    if (str.length > 1) {
//	        userDto.setFirstName(str[0]);
//	        userDto.setLastName(str[1]);
//	    } else {
//	        userDto.setFirstName(str[0]);
//	        userDto.setLastName(""); // Or handle appropriately
//	    }
//	    userDto.setEmail(user.getEmail());
//	    return userDto;
	}
	
	
}
