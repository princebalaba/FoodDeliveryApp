package com.hibernate.demo.payload.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hibernate.demo.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
//@Entity
public class Address {
	@Id
	private long id;
	@NotNull
	@Column(name = "house_no")
	private Integer houseno;
	@NotBlank
	@Column(name = "street")
	private String street;
	@NotBlank
	@Column(name = "city")
	private String city;
	@NotBlank
	@Column(name = "state")
	private String state;
	@NotNull
	@Column(name = "zipCode")
	private long zip;
	
	private User user;

}
