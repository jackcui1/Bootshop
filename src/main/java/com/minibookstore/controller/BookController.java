package com.minibookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.minibookstore.model.Book;
import com.minibookstore.service.BookService;
import com.minibookstore.service.StorageFileService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired 
	private BookService bookService;
	
	@Autowired
	private StorageFileService storageService;
	
	@RequestMapping("/view/{id}")
	public String viewBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		String getFilename=MvcUriComponentsBuilder
				.fromMethodName(BookController.class,
						"getFile", book.getImagename()).build().toString();
		book.setAbsolutImagename(getFilename);
		
		model.addAttribute("book", book);
		
		return "bookDetail";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String bookList(Model model) {
		List<Book> books = bookService.getBookList();
		for(Book book:books){
			String getFilename=MvcUriComponentsBuilder
					.fromMethodName(BookController.class,
							"getFile", book.getImagename()).build().toString();
			book.setAbsolutImagename(getFilename);
		}
		model.addAttribute("books", books);
		return "books";
	}
	
	@GetMapping("/imgfiles/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
