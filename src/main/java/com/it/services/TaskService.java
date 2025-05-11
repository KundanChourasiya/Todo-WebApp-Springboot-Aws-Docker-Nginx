package com.it.services;

import java.util.List;

import com.it.todo.entity.TaskEntity;

public interface TaskService { 

	// save task data
	public Boolean saveTask(TaskEntity entity);

	// get all task data
	public List<TaskEntity> getallTask(Long sid);

	// delete task data
	public void deleteTask(Long id);

}
