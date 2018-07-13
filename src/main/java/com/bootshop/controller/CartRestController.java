package com.bootshop.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootshop.model.Cart;
import com.bootshop.model.CartItem;
import com.bootshop.service.CartItemService;
import com.bootshop.service.CartService;
import com.bootshop.service.CustomerService;
import com.bootshop.service.ProductService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest/cart")
public class CartRestController {

	// Inject cookie name via application.properties
	@Value("${Bootshop_Cart_Cookie_Name}")
	private String cookieName;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	public CustomerService customerService;

	private String getCurrentUsername(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@RequestMapping("/get")
	public Cart getOrCreateCart(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {

		Cart cart = null;
		if (getCurrentUsername() == "anonymousUser") {
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
			if (cart == null) {
				String sessionId = request.getSession(true).getId();
				cart = new Cart(sessionId);
			}
		} else {
			cart = customerService.getCustomerBycustomername(getCurrentUsername()).getCart();
		}
		return cart;
	}

	@RequestMapping(value = "/add/{productid}", method = RequestMethod.GET)
	public void addtoCart(@PathVariable int productid,
			HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		Cart cart = getOrCreateCart(request, response);
		if (productid != 0) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(productService.getProductById(productid));
			cartItem.setCart(cart);
			cartItem.setTotalPrice(productService.getProductById(productid)
					.getPrice());
			cartItem.setQuantity(1);
			cart.addCartItem(cartItem);
		}
		updateCart(cart, request, response);
	}

	@RequestMapping("/update")
	public void updateCart(Cart cart, HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		if (getCurrentUsername().equals("anonymousUser")) {
			Writer writer = new StringWriter();
			objectMapper.writeValue(writer, cart);
			Cookie cookie = new Cookie(cookieName, URLEncoder.encode(
					writer.toString(), "UTF-8"));
			cookie.setPath("/");
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
		} else {
			customerService.getCustomerBycustomername(getCurrentUsername()).setCart(cart);
			cartService.addCart(cart);
			cartItemService.addOrUpdateCartItem(cart.getCartItems());
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setPath("/");
			cookie.setMaxAge(-0);
			response.addCookie(cookie);
		}
	}

	@RequestMapping(value = "/items/count", method = RequestMethod.GET)
	public Map<String, Integer> getCartItemCount(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		Cart cart = getOrCreateCart(request, response);
		int itemCount = cart.getQuantities();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", itemCount);
		return map;
	}

	@RequestMapping(value = "/items/grandtotal", method = RequestMethod.GET)
	public Map<String, Double> getCartGrandTotal(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		Cart cart = getOrCreateCart(request, response);
		double grandTotal = cart.getGrandTotal();
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("grandtotal", grandTotal);
		return map;
	}

	@RequestMapping(value = "/remove/{productid}", method = RequestMethod.GET)
	public void removeById(@PathVariable int productid,
			HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		Cart cart = getOrCreateCart(request, response);
		CartItem cartItem = new CartItem();
		cartItem.setProduct(productService.getProductById(productid));
		cartItem.setCart(cart);
		cartItem.setTotalPrice(productService.getProductById(productid)
				.getPrice());
		cartItem.setQuantity(1);
		if (!getCurrentUsername().equals("anonymousUser")) {
			cartItemService.deleteByProductid(cartItem.getProduct().getProductid());
		}
		cart.removeCartItem(cartItem);
		updateCart(cart, request, response);
	}

	@RequestMapping(value = "/remove/all")
	public void removeAll(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		Cart cart = getOrCreateCart(request, response);
		cart.removeAllCartItem();
		if (!getCurrentUsername().equals("anonymousUser")) {
			cartItemService.deleteByCartid(cart.getCartid());
		}
		updateCart(cart, request, response);
	}
}
