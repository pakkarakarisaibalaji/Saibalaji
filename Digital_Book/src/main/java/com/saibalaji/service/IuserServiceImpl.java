package com.saibalaji.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saibalaji.entity.Users;
import com.saibalaji.repository.IUserRepo;

@Service
public class IuserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepo repo;
	
	public String save(Users user) {
		Users userSaved  = repo.save(user);
		return "User Saved in data Base Sucesfully";
	}

	public String update(Users user) {
		Users userUpdated  = repo.save(user);
		return "User data is updated in data Base Sucesfully" ;
	}

	
	public List<Users> getAllUsers() {
		List<Users> list = repo.findAll();
		return list;
	}

	public Users getUserById(Integer id) {
		Optional<Users> findById = repo.findById(id);
		
		if(findById.isPresent())
		{
			return (findById.get());
		}
			
		return null;
	}

	public String deleteUserById(Integer id) {
		repo.deleteById(id);
		
		
		return "User is deleted from data Base sucessfully ";
	}

	

}
