package com.saibalaji.service;

import java.util.List;

import com.saibalaji.entity.Users;

public interface IUserService {
	
	
	
	//save 
	
	public String save(Users user);
	
	//update
	
	public String update(Users user);
	
	//get all users list
	
	public List<Users> getAllUsers();
	
	//get single user using pk
	
	public Users getUserById(Integer id);
	
	//delete the user based on pk
	
	public String deleteUserById(Integer id);
	

}
