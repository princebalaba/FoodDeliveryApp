package com.hibernate.demo.payload.request;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibernate.demo.dto.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private int userId;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	@NotNull
	private String password;
	@NotEmpty
	@Embedded
	private Set <Address> address;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;
	@NotEmpty
	private Set<String> roles;
	
	//user Role
	//

}
