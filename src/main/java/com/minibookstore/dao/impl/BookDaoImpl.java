package com.minibookstore.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
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
	
	public void addBook(Book book) {
		session().save(book);
	}

	@Override
	public Book getBookById(int id) {
		Book book = (Book) session().get(Book.class, id);
		session().flush();
		return book;
	}

	@Override
	public void deleteBook(Book book) {
		session().delete(book);

	}

	@Override
	public void editBook(Book book) {
		session().saveOrUpdate(book);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBookList() {
		Criteria crit = session().createCriteria(Book.class);
		return crit.list();
		
		//Error code. 
		//String hql = "from book";
		//return (List<Book>) entityManager.createQuery(hql).getResultList();
		
	}

}
