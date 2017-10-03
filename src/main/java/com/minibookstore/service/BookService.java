package com.minibookstore.service;

import java.util.List;

import com.minibookstore.model.Book;

public interface BookService {
	void addBook(Book book);
	Book getBookById(int id);
	void deleteBook(Book book);
	void editBook(Book book);
	List<Book> getBookList();
}
