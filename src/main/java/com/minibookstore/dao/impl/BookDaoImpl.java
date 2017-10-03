package com.minibookstore.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minibookstore.dao.BookDao;
import com.minibookstore.model.Book;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

	@Autowired
	public SessionFactory sessionFactory;
	
	@PersistenceContext	
	private EntityManager entityManager;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addBook(Book book) {

	}

	@Override
	public Book getBookById(int id) {
		Book book = (Book) session().get(Book.class, id);
		session().flush();
		return book;
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editBook(Book book) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBookList() {
//		Query query = session().createQuery("from book");
//		List<Book> bookList = (List<Book>)query.list();
//		session().flush();
//		return bookList;
		String hql = "FROM book";
		return (List<Book>) entityManager.createQuery(hql).getResultList();
		
	}

}
