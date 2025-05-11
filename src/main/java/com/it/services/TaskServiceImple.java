package com.it.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.repository.TaskEntityRepository;
import com.it.todo.entity.TaskEntity;

@Service
public class TaskServiceImple implements TaskService {

	// inject TaskEntityRepository dependency
	@Autowired
	private TaskEntityRepository taskRepo;

	@Override
	public Boolean saveTask(TaskEntity entity) {
		TaskEntity save = taskRepo.save(entity);
		if (save != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<TaskEntity> getallTask(Long sid) {
		List<TaskEntity> tasks = taskRepo.getTask(sid);
		return tasks;
	}

	@Override
	public void deleteTask(Long id) {
		taskRepo.deleteById(id);
	}

}
