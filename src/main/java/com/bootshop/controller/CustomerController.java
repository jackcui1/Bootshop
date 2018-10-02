package com.bootshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bootshop.model.Cart;
import com.bootshop.model.Customer;
import com.bootshop.model.Role;
import com.bootshop.model.ShippingAddress;
import com.bootshop.model.User;
import com.bootshop.service.CartService;
import com.bootshop.service.CustomerService;
import com.bootshop.service.ShippingAddressService;
import com.bootshop.service.UserService;

@SessionAttributes({ "cusotmer" })
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ShippingAddressService shippingAddressService;

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(Model model) {
	/*	Customer customer = new Customer();
		User user = new User();
		user.setEnabled(1);
		user.setRole(new Role("ROLE_USER",user.getUsername()));
		customer.setUser(user);
		customer.setShippingAddress(new ShippingAddress());
		model.addAttribute("customer", customer);
		logger.info("======" + customer);*/
		return "restRegisterForm";
	}

	/*@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addCustomer(Customer customer,HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart = new Cart(sessionId);
		customer.setCart(cart);
		logger.info("Customer======" + customer);
		cartService.addCart(customer.getCart());
		User user = customer.getUser();
		user.setEnabled(1);
		user.setRole(new Role("ROLE_USER",user.getUsername()));
		userService.save(user);
		logger.info("shippingAddress==>"+customer.getShippingAddress());

		shippingAddressService.addAddress(customer.getShippingAddress());
		customerService.addCustomer(customer);
		
		return "redirect:/login";
	}*/
}
