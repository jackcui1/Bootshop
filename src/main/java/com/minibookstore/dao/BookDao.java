package com.minibookstore.dao;

import java.util.List;

import com.minibookstore.model.Book;

public interface BookDao {
	void addBook(Book book);
	Book getBookById(int id);
	void deleteBook(Book book);
	void editBook(Book book);
	List<Book> getBookList();
}
