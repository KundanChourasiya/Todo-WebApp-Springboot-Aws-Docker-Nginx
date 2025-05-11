package com.it.todo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "todo_user")
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long uId;

	@Column(name = "uname")
	private String uName;

	@Column(name = "uemail")
	private String uEmail;

	@Column(name = "ugender")
	private String uGender;

	@Column(name = "uphone")
	private String uPhone;

	@Column(name = "upassword")
	private String uPassword;

	@CreationTimestamp
	@Column(name = "ucdate", updatable = false)
	private LocalDate createDate;

	@UpdateTimestamp
	@Column(name = "uudate", insertable = false)
	private LocalDate updateDate;

}
