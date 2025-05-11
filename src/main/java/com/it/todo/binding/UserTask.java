package com.it.todo.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserTask {

	private Long tId;

	@NotBlank(message = "This Field is required..")
	private String tName;

	@NotBlank(message = "This Field is required..")
	private String tDate;

	@NotBlank(message = "This Field is required..")
	private String tTime;

	@NotNull(message = "This Field is required..")
	private Boolean checkbtn;

}
