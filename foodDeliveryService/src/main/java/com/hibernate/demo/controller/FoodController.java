package com.hibernate.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import com.hibernate.demo.dto.Food;
import com.hibernate.demo.dto.User;
import com.hibernate.demo.exception.NoDataFoundException;
import com.hibernate.demo.repo.FoodRepo;
import com.hibernate.demo.response.UserResponse;
import com.hibernate.demo.service.FoodService;

@RestController // -----> (@Controller and @ResponseBody)
@RequestMapping("/food")

@Validated // it activates the use of @Min in the getFoodById

	
public class FoodController {
	@Autowired
	private FoodRepo foodRepo;	
	
	@Autowired
	private FoodService foodService;
	//add food Item 
	@PostMapping(value = "/addFood")
	public ResponseEntity<?> createFood(@Valid @RequestBody Food food ) {
		Food food2 = foodRepo.save(food);
		return ResponseEntity.status(201).body(food2);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") @Min(1) Long id) {
	Food food = foodRepo.findById(id).orElseThrow(()-> new NoDataFoundException("No data found"));
		return ResponseEntity.ok(food);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAllFood() {
		List<Food> list = foodService.getAllFoods();
		if(list.size() >0) {
			return ResponseEntity.ok(list);
		}else {
			throw new NoDataFoundException("Sorry Food Record  Not found");
		}
	}
	
	//Descending Order
	@GetMapping(value ="/all/desc")
	public ResponseEntity<?> getAllDescOrder(){
		List <Food> list  = foodRepo.findAll();
		Collections.sort(list, (a,b) -> b.getId().compareTo(a.getId()));
		return ResponseEntity.status(200).body(list);
	}
	//Ascending order
	@GetMapping(value ="/all/asc")
	public ResponseEntity<?> getAllAscOrder(){
		List <Food> list  = foodRepo.findAll();
		Collections.sort(list, (a,b) -> a.getId().compareTo(b.getId()));
		return ResponseEntity.status(200).body(list);
	}
	



}
