package com.it.todo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "todo_task")
@Data
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tid")
	private Long tId;

	@Column(name = "tname")
	private String tName;

	@Column(name = "tdate")
	private String tDate;

	@Column(name = "ttime")
	private String tTime;

	@CreationTimestamp
	@Column(name = "tcdate", updatable = false)
	private LocalDate createDate;

	@UpdateTimestamp
	@Column(name = "tudate", insertable = false)
	private LocalDate updateDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tid_uid")
	private UserEntity users;
}
