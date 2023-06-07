package com.dreamthoughts.springcustomvalidaterequestbody.model;

import com.dreamthoughts.springcustomvalidaterequestbody.validation.PasswordMatching;
import com.dreamthoughts.springcustomvalidaterequestbody.validation.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatching(password = "password", confirmPassword = "confirmPassword", message = "Password and Confirm Password must be matched!")
public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@StrongPassword
	private String password;

	private String confirmPassword;
}
