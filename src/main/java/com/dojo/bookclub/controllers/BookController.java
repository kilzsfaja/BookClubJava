package com.dojo.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.dojo.bookclub.models.Book;
import com.dojo.bookclub.services.BookService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
		
	@GetMapping("/books")
	public String bookDashboard(HttpSession session) {
		if(session.getAttribute("userId")== null) {
			return "redirect:/";
		}
		return "dashboard.jsp";
	}
	
//	---- CREATE ONE (ROUTE) ----
	@GetMapping("/books/create")
	public String createBook(@ModelAttribute("book")Book book) {
		return "create.jsp";
	}
	
//	---- CREATE ONE (FORM) ----
	@PostMapping("/books/process/create")
	public String processCreateBook(@Valid @ModelAttribute("book")Book book, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "create.jsp";
		}
		bookService.create(book);
		return "redirect:/";
	}
	
//	---- EDIT ONE (ROUTE) ----
	@GetMapping("/books/edit/{id}")
	public String editBook(@PathVariable("id")Long id, Model model) {
		model.addAttribute("book", bookService.findOne(id));
		return "edit.jsp";
	}
	
//	---- EDIT ONE (FORM) ----
	@PutMapping("/books/process/edit/{id}")
	public String processEditBook(@Valid @ModelAttribute("book")Book book, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edit.jsp";
		}
		bookService.update(book);
		return "redirect:/books";
	}
	
//	---- SHOW ONE ----
	@GetMapping("/books/{id}")
	public String showOneBook(Model model, @PathVariable("id")Long id) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "showOne.jsp";
	}
	
	
//	---- DELETE ----
	@DeleteMapping("/books/delete/{id}")
	public String deleteBook(@PathVariable("id")Long id) {
		bookService.delete(id);
		return "redirect:/books";
	}
	
}
