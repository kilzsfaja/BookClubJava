package com.dojo.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dojo.bookclub.services.BookService;
import com.dojo.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;

	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		if(session.getAttribute("user_id")== null) {
			return "redirect:/users/login/register";
		}
		model.addAttribute("allBooks", bookService.getAll());
		model.addAttribute("loggedInUser", userService.getUser((Long)session.getAttribute("user_id")));
		return "dashboard.jsp";
	}

}