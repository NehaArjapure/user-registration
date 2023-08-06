package com.d.urp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d.urp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByAge(int age);

	Optional<User> findByUsernameAndPassword(String username, String password);

}
