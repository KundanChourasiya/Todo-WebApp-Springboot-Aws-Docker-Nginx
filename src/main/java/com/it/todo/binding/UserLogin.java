package com.it.todo.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserLogin {

	@NotBlank(message = "This Field is required..")
	@Email(message = "Enter Valid email address...")
	private String uEmail;

	@NotBlank(message = "This Field is required..")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Password must have of minimum 8 Characters and at least one uppercase letter, one lowercase letter, one number and one special character")
	private String uPassword;

	@NotNull(message = "This Field is required..")
	private Boolean checkbtn;

}
