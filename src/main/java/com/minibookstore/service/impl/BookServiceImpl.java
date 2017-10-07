package com.minibookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibookstore.dao.BookDao;
import com.minibookstore.model.Book;
import com.minibookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	public BookDao bookDao;

	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public void editBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public List<Book> getBookList() {
		return bookDao.getBookList();
	}
	

}
