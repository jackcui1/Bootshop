package com.bootshop.service;

import com.bootshop.model.Article;
import com.bootshop.model.Contact;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 2018/11/10 8:37
 */
public interface ContactService {

    public List<Contact> findAll();
    public Contact findById(Integer id);
    public Contact save(Contact contact);
    public Boolean findByEmail(String email);
    public void deleteById(Integer id);
}
