package com.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String string);

}
