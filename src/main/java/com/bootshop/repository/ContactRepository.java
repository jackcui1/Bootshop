package com.bootshop.repository;


import com.bootshop.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @author Guowei Cui
 * @date 2018/11/10 8:35
 */
public interface ContactRepository extends JpaRepository<Contact, Integer>{
    Boolean findContactByEmail(String email);
}

