package com.example.cs5610spring2019javaserveradityalprabhu.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.User;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.*;


@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class UserService {
	User john = new User("john", "password", "John", "Doe", "8473843434", "john@email.com", "Student", "1990-12-01");
	User jane   = new User( "jane", "password", "Jane", "Doe", "8473843434", "jane@email.com", "Faculty", "1965-12-12");
	List<User> users = new ArrayList<User>();


	@PostConstruct
	public void init() {
		this.users = (List<User>) userRepository.findAll();
		if(this.users.size() == 0) {
			userRepository.save(john);
			userRepository.save(jane);
		}
		this.users = (List<User>) userRepository.findAll();	
	}



	@Autowired
	UserRepository userRepository;


	@GetMapping("/api/users")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@GetMapping("/api/users/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {

		User user;

		try {
			user = userRepository.findById(id).get();
			return user;
		}catch(Exception e) {
			return null;
		}
	}



	@PostMapping("/api/user")
	public User createUser
	(@RequestBody User user) {

		return userRepository.save(user);
	}



	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") Integer id, @RequestBody User updated_user) {

		User user = userRepository.findById(id).get();
		user.setUsername(updated_user.getUsername());
		user.setPassword(updated_user.getPassword());
		user.setFirstName(updated_user.getFirstName());
		user.setLastName(updated_user.getLastName());
		user.setRole(updated_user.getRole());
		user.setDob(updated_user.getDob());
		user.setEmail(updated_user.getEmail());
		return userRepository.save(user);
	}

	@DeleteMapping("/api/user/{userId}")
	public String deleteUser(@PathVariable("userId") Integer id) {


		try{
			userRepository.findById(id).get();
			userRepository.deleteById(id);
			return "User Deleted";
		}catch(Exception e) {
			return "User not found";
		}
	}


	@PostMapping("/api/register")
	public User register(@RequestBody User user,
			HttpSession session) {

		String username = user.getUsername();
		if(userRepository.findUserByUsername(username).size() != 0) {
			return null;
		}else {
			userRepository.save(user);
			User newUser = userRepository.findUserByUsername(username).get(0);
			session.setAttribute("currentUser", newUser);

			return newUser;
		}

	}


	@PostMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)
				session.getAttribute("currentUser");	
		return currentUser;
	}

	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) {

		User updated_user = userRepository.findById(user.getId()).get();

		if(updated_user != null) {

			updated_user.setUsername(user.getUsername());
			updated_user.setRole(user.getRole());
			updated_user.setDob(user.getDob());
			updated_user.setEmail(user.getEmail());
			updated_user.setPhone(user.getPhone());
			session.setAttribute("currentUser", updated_user);
			return userRepository.save(updated_user);

		}else {
			return null;
		}
	}

	@PostMapping("/api/login")
	public User login(	@RequestBody User credentials,
			HttpSession session) {

		List<User> userList = userRepository.findUserByUsername(credentials.getUsername());

		if(userList.size() != 1) {
			return null;
		}else {
			User curr_user = userList.get(0);
			if(curr_user.getPassword().equalsIgnoreCase(credentials.getPassword())) {
				session.setAttribute("currentUser", curr_user);
				return curr_user;
			}else {
				return null;
			}
		}
	}


	@PostMapping("/api/logout")
	public void logout
	(HttpSession session) {
		session.invalidate();
	}



}














