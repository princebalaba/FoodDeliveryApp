package com.hibernate.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.demo.dto.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	//public boolean existById(long id);
	public List<User> findByName(String name);

	public boolean existsByEmail(String email);

	public boolean existsByName(String name);

	public Optional<User> findByEmail(String email);

}
