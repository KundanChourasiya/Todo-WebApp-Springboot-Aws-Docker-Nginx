package com.it.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.it.services.TaskService;
import com.it.todo.binding.UserTask;
import com.it.todo.entity.TaskEntity;
import com.it.todo.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TaskController {

	// inject TaskService dependency
	@Autowired
	private TaskService taskService;

	// all task details
	@GetMapping("/yourtask")
	public String taskBoard(Model model, RedirectAttributes attributes, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession(false);

		if (session != null) {
			UserEntity userEntity = (UserEntity) session.getAttribute("logingUser");

			if (userEntity != null) {

				List<TaskEntity> getallTask = taskService.getallTask(userEntity.getUId());
				model.addAttribute("getallTask", getallTask);
				model.addAttribute("users", userEntity);
				model.addAttribute("page_title", "User Task");
				return "tasklist";
			}
		}
		attributes.addFlashAttribute("msg", "Please enter login details...");
		return "redirect:/login";
	}

	// task From
	@GetMapping("/addtask")
	public String loadTaskFrom(Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) {

		HttpSession session = request.getSession(false);

		if (session != null) {
			UserEntity userEntity = (UserEntity) session.getAttribute("logingUser");

			if (userEntity != null) {
				UserTask userTask = new UserTask();
				model.addAttribute("userTask", userTask);
				model.addAttribute("users", userEntity);
				model.addAttribute("page_title", "Add your task");
				return "task_form";
			}

		}
		attributes.addFlashAttribute("msg", "Please fill User Login details...");
		return "redirect:/login";
	}

	// task From submit
	@PostMapping("/addtask")
	public String saveTask(@Valid UserTask task, BindingResult bindingResult, RedirectAttributes attributes,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(task);

		HttpSession session = request.getSession(false);

		if (session != null) {
			UserEntity userEntity = (UserEntity) session.getAttribute("logingUser");

			if (bindingResult.hasErrors()) {
				model.addAttribute("msg", "Please enter task details...");
				model.addAttribute("users", userEntity);
				model.addAttribute("page_title", "Add your task");
				return "task_form";
			}
			if (userEntity != null) {
				TaskEntity taskEntity = new TaskEntity();
				BeanUtils.copyProperties(task, taskEntity);
				taskEntity.setUsers(userEntity);
				System.out.println(taskEntity);
				taskService.saveTask(taskEntity);
				attributes.addFlashAttribute("msg", "Task created successfully...");
				return "redirect:/addtask";
			}

		}
		return "redirect:/login";
	}

	// delete task details
	@GetMapping("/yourtask/delete/{id}")
	public String deleteTask(@PathVariable Long id, RedirectAttributes attributes, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession(false);

		if (session != null) {
			UserEntity userEntity = (UserEntity) session.getAttribute("logingUser");

			if (userEntity != null) {
				taskService.deleteTask(id);
				attributes.addFlashAttribute("msg", "Task Comppleted successfully");
				return "redirect:/yourtask";
			}

		}
		return "redirect:/login";
	}
}
