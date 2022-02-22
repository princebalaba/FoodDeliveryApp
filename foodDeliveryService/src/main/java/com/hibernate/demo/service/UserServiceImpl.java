package com.hibernate.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.demo.dto.User;
import com.hibernate.demo.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;

	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Optional<User> getUserById(long id) {

		return userRepo.findById(id);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public String deleteUserById(long id) {
		userRepo.deleteById(id);
		return "Deleted successfully";
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsersAscOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsersDescOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existById(long id) {
		// TODO Auto-generated method stub
		return userRepo.existsById(id);
	}

}
