package com.dojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojo.bookclub.models.Book;
import com.dojo.bookclub.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

    // ---- CREATE ONE ----
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    // ---- FIND ALL ----
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    //  ---- FIND ONE ----
    public Book findOne(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
	// ---- EDIT ONE ----
    public Book update(Book book) {
        return bookRepository.save(book);
    }
    
    //    ---- DELETE ONE ----
    public void delete(Long id) {
    	bookRepository.deleteById(id);
    }
}
