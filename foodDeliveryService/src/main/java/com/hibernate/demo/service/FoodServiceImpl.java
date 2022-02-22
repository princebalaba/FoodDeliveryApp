package com.hibernate.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.demo.dto.Food;
import com.hibernate.demo.dto.User;
import com.hibernate.demo.enums.FoodType;
import com.hibernate.demo.repo.FoodRepo;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	FoodRepo foodRepo;
	
	@Override
	public Food addFood(Food food) {
		return foodRepo.save(food);
		
	}

	@Override
	public Optional<Food> getFoodById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Food> getAllFoods() {
		return foodRepo.findAll();
		
	}

	@Override
	public String deleteFoodById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateFood(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllFoodAscOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllFoodDescOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Food> getFoodByType(FoodType foodType) {
		//foodRepo.get(foodType);
		return null;
	}

}
