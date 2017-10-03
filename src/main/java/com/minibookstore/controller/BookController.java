package com.minibookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minibookstore.model.Book;
import com.minibookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	public BookService bookService;

	@RequestMapping("/viewBook/{id}")
	public Book viewBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return book;
	}
	@RequestMapping("/list")
	public List<Book> bookList(Model model) {
		List<Book> books = bookService.getBookList();
		model.addAttribute("books", books);
		return books;
		
	}
}
