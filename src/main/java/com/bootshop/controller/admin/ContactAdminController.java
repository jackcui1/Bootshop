package com.bootshop.controller.admin;

import com.bootshop.model.Contact;
import com.bootshop.model.Product;
import com.bootshop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 2018/11/11 22:27
 */

@Controller
@RequestMapping("/admin")
public class ContactAdminController {

    @Autowired
    private ContactService contactService;


    @RequestMapping("/contactmanagement")
    public String ListContact(Model model) {
        List<Contact> contacts = contactService.findAll();
        model.addAttribute("contacts", contacts);
        return "contactManagement";
    }

    @RequestMapping("/deletecontact/{id}")
    public String deleteContact(@PathVariable Integer id, Model model) {
        contactService.deleteById(id);
        return "redirect:/admin/contactmanagement";
    }
}
