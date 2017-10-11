package com.minibookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minibookstore.model.Book;
import com.minibookstore.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired 
	private BookService bookService;

	@RequestMapping("/view/{id}")
	public String viewBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		
		return "bookDetail";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String bookList(Model model) {
		List<Book> books = bookService.getBookList();
		model.addAttribute("books", books);
		return "books";
	}
	
//	@RequestMapping("/list",method=RequestMethod.GET)
//	public String bookList(Model model,HttpServletRequest request) {
//		int page = Integer.parseInt(request.getParameter("page"));
//		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//
//		Pageable pageable = new PageRequest(page, pageSize);
//		List<Book> books = bookService.getBookList(pageable);
//		model.addAttribute("books", books);
//		return "books";
//	}
//	
////	@RequestMapping("/list/pageable")
//	 public String showBooks(Model model, 
//		      @Qualifier("foo") Pageable first,
//		      @Qualifier("bar") Pageable second){
//		return "books";
//	}
}
