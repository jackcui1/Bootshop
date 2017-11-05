package com.bootshop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootshop.model.Cart;
import com.bootshop.model.CartItem;
import com.bootshop.model.Customer;
import com.bootshop.model.CustomerOrder;
import com.bootshop.service.CartItemService;
import com.bootshop.service.CartService;
import com.bootshop.service.CustomerOrderService;
import com.bootshop.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class OrderController {

	// Inject cookie name via application.properties
	@Value("${Bootshop_Cart_Cookie_Name}")
	private String cookieName;

	@Autowired
	CartService cartService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartItemService cartItemService;

	@Autowired
	CustomerOrderService customerOrderService;

	private String getCurrentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@RequestMapping(value = "/order/{cartid}", method = RequestMethod.GET)
	public String createOrder(@PathVariable String cartid,
			HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException,
			UnsupportedEncodingException, IOException {
		Cart cart = null;
		if (getCurrentUsername().equals("anonymousUser")) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			Cookie[] cookies = request.getCookies();
			// 1.read cart from cookie.
			if (null != cookies && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookieName.equals(cookie.getName())
							&& cookie.getValue() != "") {
						cart = objectMapper.readValue(
								URLDecoder.decode(cookie.getValue(), "UTF-8"),
								Cart.class);
						break;
					}
				}
			}
			// 2. Create cart if cart is null.
			if (cart.getCartid().equals(cartid)) {
				for(CartItem cartItem: cart.getCartItems()){
					cartItem.setCart(cart);
				}
					
				cartService.addCart(cart);
				cartItemService.addOrUpdateCartItems(cart.getCartItems());
				return "redirect:/checkout?cartid=" + cartid;
			} else {
				return "invalidCartWarning";
			}
		} else {

			/*
			 * CustomerOrder customerOrder = new CustomerOrder(); Cart cart =
			 * cartService.getCartById(cartid); customerOrder.setCart(cart);
			 * Customer customer =
			 * customerService.getCustomerBycustomername(getCurrentUsername());
			 * customerOrder.setCustomer(customer);
			 * customerOrder.setShippingAddress(customer.getShippingAddress());
			 * customerOrderService.addCustomerOrder(customerOrder);
			 */
			return "redirect:/checkout?cartid=" + cartid;
		}
	}
}
