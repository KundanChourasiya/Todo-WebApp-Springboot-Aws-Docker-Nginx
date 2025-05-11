package com.it.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.it.services.UserService;
import com.it.todo.binding.UserLogin;
import com.it.todo.binding.UserRegister;
import com.it.todo.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	// inject UserService dependency
	@Autowired
	private UserService userService;

	// load user registration form
	@GetMapping("/")
	public String loadUserRegForm(Model model) {
		UserRegister userRegister = new UserRegister();
		model.addAttribute("userRegister", userRegister);
		model.addAttribute("page_title", "User Registration");
		return "reg_form";
	}

	// save user Registration data
	@PostMapping("/")
	public String saveUserReg(@Valid UserRegister user, BindingResult bindingResult, RedirectAttributes attributes,
			Model model) {
		System.out.println(user);

		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "Please fill User details...");
			model.addAttribute("page_title", "User Registration");
			return "reg_form";
		} else {
			// save user
			UserEntity users = new UserEntity();
			BeanUtils.copyProperties(user, users);
			userService.saveUser(users);
			attributes.addFlashAttribute("msg", "user registered successfully...");
			return "redirect:/";
		}
	}

	// load user login form
	@GetMapping("/login")
	public String loadUserLoginForm(Model model) {
		UserLogin userLogin = new UserLogin();
		model.addAttribute("userLogin", userLogin);
		model.addAttribute("page_title", "User Login");
		return "login_form";
	}

	// login user
	@PostMapping("/login")
	public String loginRegUser(@Valid UserLogin userLogin, BindingResult bindingResult, RedirectAttributes attributes,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(userLogin);

		if (bindingResult.hasErrors()) {
			model.addAttribute("page_title", "User Login");
			model.addAttribute("msg", "Please fill User Login details...");
			return "login_form";
		} else {
			UserEntity logingUser = userService.getLogingUser(userLogin.getUEmail(), userLogin.getUPassword());

			if (logingUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute("logingUser", logingUser);
				attributes.addFlashAttribute("page_title", "User Task");
				return "redirect:/yourtask";
			} else {
				attributes.addFlashAttribute("msg", "invalid Email and password..");
				return "redirect:/login";
			}
		}
	}

	// user logout
	@GetMapping("/logout")
	public String logoutUser(Model model, RedirectAttributes attributes, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		attributes.addFlashAttribute("msg", "logout Successfully...");
		return "redirect:/login";
	}

}
