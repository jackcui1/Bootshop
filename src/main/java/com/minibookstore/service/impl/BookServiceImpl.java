package com.minibookstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.minibookstore.model.Book;
import com.minibookstore.repository.BookRepository;
import com.minibookstore.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	public BookRepository bookRepository;
	
	
	public void addBook(Book book) {
		bookRepository.save(book);
		
	}

	public Book getBookById(int id) {
		return bookRepository.findOne(id);
	}

	public void deleteBook(Book book) {
		bookRepository.delete(book);
		
	}

	public void editBook(Book book) {
		bookRepository.save(book);
	}

	public List<Book> getBookList() {
		return bookRepository.findAll();
	}
	
	

	public Page<Book> getBookList(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
	

}
