package com.dojo.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dojo.bookclub.models.LoginUser;
import com.dojo.bookclub.models.User;
import com.dojo.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login/register")
	public String login(@ModelAttribute("newUser")User user, Model model) {
		model.addAttribute("loginUser", new LoginUser());
		return "loginreg.jsp";
	}
	
	@PostMapping("/process/register")
	public String processRegistration(@Valid @ModelAttribute("newUser")User user, BindingResult bindingResult, Model model, HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "loginreg.jsp";
		}
		
//		reject the value if email is taken
		if(userService.getUser(user.getEmail()) != null) {
			bindingResult.rejectValue("email", "unique", "Email has already been taken");
	        model.addAttribute("loginUser", new LoginUser());
	        return "loginreg.jsp";
		}
//		reject if passwords don't match
		if(!user.getPassword().equals(user.getConfirm())) {
			bindingResult.rejectValue("password", "Matches", "Password does not match!");
	        model.addAttribute("loginUser", new LoginUser());
	        return "loginreg.jsp";
		}
		User newUser = userService.createUser(user);
		session.setAttribute("user_id", newUser.getId());
		
		return "redirect:/";
	}
	
	@PostMapping("/process/login")
	public String processLogin(@Valid @ModelAttribute("loginUser")LoginUser loginUser, BindingResult bindingResult, Model model, HttpSession session) {
		User loggingUser = userService.login(loginUser, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "loginreg.jsp";
		}
		session.setAttribute("user_id", loggingUser.getId());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login/register";
	}
	
	@GetMapping("/")
	public String renderIndex() {
		return "dashboard.jsp";
	}
}
