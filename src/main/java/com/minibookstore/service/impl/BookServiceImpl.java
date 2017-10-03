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

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> getBookList() {
		return bookDao.getBookList();
	}
	

}
