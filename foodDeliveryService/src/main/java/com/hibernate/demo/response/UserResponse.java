package com.hibernate.demo.response;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonFormat;
//import com.hibernate.demo.dto.Address;
import com.hibernate.demo.payload.request.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	//@NotEmpty
	private Set <Address> address;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;
	@NotEmpty
	private Set<String> roles;
	

}
