package com.minibookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.minibookstore.model.Book;

public interface BookService {
	void addBook(Book book);
	Book getBookById(int id);
	void deleteBook(Book book);
	void editBook(Book book);
	Page<Book> getBookList(Pageable pageable);
	List<Book> getBookList();
	
}
