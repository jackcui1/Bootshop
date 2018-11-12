package com.bootshop.service;

import com.bootshop.model.Contact;
import com.bootshop.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Guowei Cui
 * @date 2018/11/10 8:39
 */

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public Contact findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Boolean findByEmail(String email) {
        return repository.findContactByEmail(email);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }
}
