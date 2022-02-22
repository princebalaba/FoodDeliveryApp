package com.hibernate.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.demo.dto.Food;

public interface FoodRepo extends JpaRepository<Food, Long> {

}
