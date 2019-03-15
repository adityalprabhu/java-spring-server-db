package com.example.cs5610spring2019javaserveradityalprabhu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610spring2019javaserveradityalprabhu.model.User;

public interface UserRepository extends CrudRepository<User, Integer> { 
	
	@Query("SELECT user FROM User user WHERE user.username=:username")
	public List<User> findUserByUsername(@Param("username") String username);
	
	@Query("SELECT user FROM User user WHERE user.id=:id")
	public User findUserById(@Param("id") Integer id);
	
}