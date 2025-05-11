package com.it.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.todo.entity.TaskEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {

	// get task data where task id= user id
	@Query(value = "SELECT * FROM todo_task WHERE tid_uid =:sid", nativeQuery = true)
	@Modifying
	@Transactional
	public List<TaskEntity> getTask(long sid);

}
