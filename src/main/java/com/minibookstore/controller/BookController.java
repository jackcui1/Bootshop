package com.minibookstore.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minibookstore.model.Book;
import com.minibookstore.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	private Path path;
	@Autowired
	public BookService bookService;

	@RequestMapping("/view/{id}")
	public String viewBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		
		return "bookDetail";
	}
	
	@RequestMapping("/add")
	public String addBook(Model model){
		Book book=new Book();
		model.addAttribute("book",book);
		return "addBook";
	}
		
	@RequestMapping("/list")
	public String bookList(Model model) {
		List<Book> books = bookService.getBookList();
		System.out.println(books);
		model.addAttribute("books", books);
		return "books";
	}
}
