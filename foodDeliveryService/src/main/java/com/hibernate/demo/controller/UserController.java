package com.hibernate.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hibernate.demo.dto.Address;
import com.hibernate.demo.dto.Role;
import com.hibernate.demo.dto.User;
import com.hibernate.demo.enums.ERole;
import com.hibernate.demo.exception.IdNotFoundException;
import com.hibernate.demo.exception.NoDataFoundException;
import com.hibernate.demo.payload.request.SignupRequest;
import com.hibernate.demo.repo.RoleRepo;
import com.hibernate.demo.response.UserResponse;
import com.hibernate.demo.service.UserService;

@RestController
// /api
@RequestMapping("/users")
public class UserController {
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserService userService;
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> DeleteUserById(@PathVariable("id")long id){
		//check if user exists
		if(userService.existById(id)) {
			//sucess part
			userService.deleteUserById(id);
			return ResponseEntity.noContent().build();
			
		}else {
			//failure part
			throw new NoDataFoundException("record not found");
			
		}
		//if so, delete user
		//if not, throw an exception
	}
	
	
	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id")long id) {
		
	User user =	userService.getUserById(id).orElseThrow(()->new NoDataFoundException("data not available"));
		// DTO ===> UserResponse()
	UserResponse userResponse=  new UserResponse();
	userResponse.setEmail(user.getEmail());
	userResponse.setName(user.getName());
	Set<String> roles= new HashSet<>();
	userResponse.setDoj(user.getDoj());
	user.getRoles().forEach(e2->{
		roles.add(e2.getRoleName().name());
	});
	Set<com.hibernate.demo.payload.request.Address> addresses = new HashSet<>();
	user.getAddresses().forEach(e3->{
		com.hibernate.demo.payload.request.Address address2 = new com.hibernate.demo.payload.request.Address();
		address2.setHouseno(e3.getHouseno());
		address2.setCity(e3.getCity());
		//address2.setCountry(e3.getCountry());
		address2.setState(e3.getState());
		address2.setStreet(e3.getStreet());
		address2.setZip(e3.getZip());
		addresses.add(address2);
	});
	userResponse.setAddress(addresses);
	userResponse.setRoles(roles);
	return ResponseEntity.status(200).body(userResponse);
	}


	@GetMapping(value = "/")
	public ResponseEntity<?> getAllUsers() {

		List<User> list = userService.getAllUsers();
		List<UserResponse> userResponses = new ArrayList<>();
		list.forEach(e -> {
			UserResponse userResponse = new UserResponse();
			userResponse.setEmail(e.getEmail());
			userResponse.setName(e.getName());
			Set<String> roles = new HashSet<>();
			userResponse.setDoj(e.getDoj());
			e.getRoles().forEach(e2 -> {
				roles.add(e2.getRoleName().name());
			});
			Set<com.hibernate.demo.payload.request.Address> addresses = new HashSet<>();
			e.getAddresses().forEach(e3 -> {
				com.hibernate.demo.payload.request.Address address2 = 
						new com.hibernate.demo.payload.request.Address();
				address2.setHouseno(e3.getHouseno());
				address2.setCity(e3.getCity());
				//address2.setCountry(e3.getCountry());
				address2.setState(e3.getState());
				address2.setStreet(e3.getStreet());
				address2.setZip(e3.getZip());
				addresses.add(address2);
			});
			//userResponse.com.hibernate.demo.dto.User.setAddresses((addresses)); ********************
			userResponse.setAddress(addresses);
			userResponse.setRoles(roles);
			userResponses.add(userResponse);
		});
		if(userResponses.size() >0)
			return ResponseEntity.ok(userResponses);
		else {
			throw new NoDataFoundException("no users found");
		}
	
		//return ResponseEntity.ok(list);
	}
	

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		// can u create user object?
		// can u initialize the values based on the signuprequest object?
		Set<Role> roles = new HashSet<>();
		if (signupRequest.getRoles() == null) {
			Role userRole = roleRepo.findByRoleName(ERole.ROLE_USER)
					.orElseThrow(() -> new IdNotFoundException("RoleId Not Found"));
			roles.add(userRole);
		} else {
			signupRequest.getRoles().forEach(e -> {

				switch (e) {
				case "user":
					Role userRole = roleRepo.findByRoleName(ERole.ROLE_USER)
							.orElseThrow(() -> new IdNotFoundException("RoleId Not Found"));
					roles.add(userRole);
					break;
				case "admin":
					Role userAdmin = roleRepo.findByRoleName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new IdNotFoundException("RoleId Not Found"));
					roles.add(userAdmin);

				default:
					break;
				}

			});
		}

		User user = new User();

		Set<Address> addresses = new HashSet<>();
		signupRequest.getAddress().forEach(e -> {
			Address address = new Address();
			address.setCity(e.getCity());
			address.setCity(e.getCity());
			address.setHouseno(e.getHouseno());
			address.setState(e.getState());
			address.setStreet(e.getStreet());
			address.setUser(user);
			address.setZip(e.getZip());
			addresses.add(address);
		});
		user.setAddresses(addresses);
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(signupRequest.getPassword());
		user.setRoles(roles);
		user.setDoj(signupRequest.getDoj());

		User user2 = userService.addUser(user);
		return ResponseEntity.status(201).body(user2);

	}

}