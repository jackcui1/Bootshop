package com.bootshop.controller.restapi;

import com.bootshop.model.Contact;
import com.bootshop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guowei Cui
 * @date 2018/11/10 7:46
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/rest/contacts")
public class ContactRestController {

    @Autowired
    private ContactService service;

    @RequestMapping("/")
    public Contact getInfo() {
        Contact contact = service.findById(1);
        return contact;

    }

    @PutMapping("/")
    public Contact update(@RequestBody Contact contact) {

        Contact contactInfo = service.findById(1);
        contact.setArticle(contactInfo.getArticle());
        return service.save(contact);
    }

}
