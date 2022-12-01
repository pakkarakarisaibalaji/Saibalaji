package com.saibalaji.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saibalaji.entity.Users;
import com.saibalaji.service.IUserService;
import com.saibalaji.utlities.UserRoutings;

@RestController
public class UserRestController {
	
	@Autowired
	private IUserService service;
	
	@PostMapping(value=UserRoutings.save)
	public ResponseEntity<String> saveUser(@RequestBody Users user)
	{
		try {
		String msg = service.save(user);
		
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Some thing went Wrong", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	@PostMapping(value=UserRoutings.update)
	public ResponseEntity<String> updateUser(@RequestBody Users user)
	{
		String update = service.update(user);
		
		return new ResponseEntity<String>(update, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value=UserRoutings.getAllUsers)
	public ResponseEntity<List<Users>> getAllUsers()
	{
		List<Users> list = service.getAllUsers();
		return new ResponseEntity<List<Users>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping(value=UserRoutings.getUserById)
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id)
	{
		
		Users user = service.getUserById(id);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value=UserRoutings.delete)
	public ResponseEntity<String> deleteUserByID(@PathVariable("id") Integer id)
	{
		
		String body = service.deleteUserById(id);
		return new ResponseEntity<String>(body, HttpStatus.OK);
		
	}
	

}
