package com.rest.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dao.UserRepository;
import com.rest.entity.User;
import com.rest.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService; 

	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") Integer id){
		return userRepository.findById(id).get();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/users/add")
	public User addUser(@RequestBody String username, @RequestBody String password, @RequestBody String confirmedPassword ) {
		return userService.saveUser(username, password, confirmedPassword);
	}
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
		User temp = userRepository.findById(id).get();
		temp.setUserId(user.getUserId());
		temp.setPassword(user.getPassword());
		temp.setUsername(user.getUsername());
		return userRepository.save(temp);
		
	}

}
