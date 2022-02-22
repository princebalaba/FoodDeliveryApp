package com.hibernate.demo.service;

import java.util.List;
import java.util.Optional;

import com.hibernate.demo.dto.User;

public interface UserService {
	public User addUser(User user);

	public Optional<User> getUserById(long id);

	public List<User> getAllUsers();

	public String deleteUserById(long id);

	public User updateUser(User user);

	public List<User> getAllUsersAscOrder();

	public List<User> getAllUsersDescOrder();

	public boolean existById(long id);

}
