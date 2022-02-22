package com.hibernate.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hibernate.demo.dto.Food;
import com.hibernate.demo.dto.User;
import com.hibernate.demo.enums.FoodType;
@Service
public interface FoodService {
	public Food addFood(Food food);

	public Optional<Food> getFoodById(long id);
	
	public Optional<Food> getFoodByType(FoodType foodType);

	public List<Food> getAllFoods();

	public String deleteFoodById(long id);

	public User updateFood(User user);

	public List<User> getAllFoodAscOrder();

	public List<User> getAllFoodDescOrder();

	public boolean existById(long id);


}
