package com.taskManager.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
//	Optional<User> findUsernameOrEmail(String username, String email);
//	Optional<User> findByUsername(String username);

//	Boolean existsByEmail(String email);
//	Optional<User> findByUsernameOrEmail(String username, String email);
	
//	==============================================================
//	Optional<User> findByEmail(String email); // should not require any changes if using default JPA

	User findByEmail(String email);
//	Optional<User> findByEmail(String email);
}
