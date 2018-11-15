package com.bootshop.repository;


import com.bootshop.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author Guowei Cui
 * @date 2018/11/10 8:35
 */
public interface ContactRepository extends JpaRepository<Contact, Integer>{

    public Contact findByEmail(String email);
}

