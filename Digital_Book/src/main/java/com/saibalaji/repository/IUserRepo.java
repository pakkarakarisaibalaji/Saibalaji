package com.saibalaji.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saibalaji.entity.Users;

public interface IUserRepo extends JpaRepository<Users, Integer> {

}
