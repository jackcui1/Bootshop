package com.bootshop.service.impl;

import com.bootshop.common.CommonConstants;
import com.bootshop.model.Cart;
import com.bootshop.model.CartItem;
import com.bootshop.model.Product;
import com.bootshop.service.CartCookieService;
import com.bootshop.service.ProductService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Guowei Cui
 * @date 8/16/2018 2:14 PM
 */

@Service
public class CartCookieServiceImpl implements CartCookieService {

    @Autowired
    ProductService productService;

    @Override
    public Cart createOrGet(HttpServletRequest request, HttpServletResponse reponse) throws IOException {
        String sessionId = request.getSession(true).getId();
        Cart cart = new Cart(sessionId);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, cart);
        Cookie[] cookies = request.getCookies();
        // 1.read cart from cookie.
        boolean isExistCookie = false;
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (CommonConstants.BOOTSHOP_COOKIE_NAME.equals(cookie.getName())
                        && cookie.getValue() != "") {
                    cart = objectMapper.readValue(
                            URLDecoder.decode(cookie.getValue(), "UTF-8"),
                            Cart.class);
                    isExistCookie = true;
                    break;
                }
            }
        }

        if (!isExistCookie) {
            Cookie cookie = new Cookie(CommonConstants.BOOTSHOP_COOKIE_NAME, URLEncoder.encode(
                    writer.toString(), "UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(CommonConstants.BOOTSHOP_COOKIE_MAXAGE);
            reponse.addCookie(cookie);
        }
        return cart;
    }

    @Override
    public Cart update(Cart cart, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, cart);
        Cookie cookie = new Cookie(CommonConstants.BOOTSHOP_COOKIE_NAME, URLEncoder.encode(
                writer.toString(), "UTF-8"));
        cookie.setPath("/");
        cookie.setMaxAge(CommonConstants.BOOTSHOP_COOKIE_MAXAGE);
        response.addCookie(cookie);
        return cart;
    }

    @Override
    public Cart addItem(Cart cart, Product product, HttpServletResponse response) throws IOException {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setTotalPrice(product.getPrice());
        cartItem.setQuantity(1);
        cart.addCartItem(cartItem);
        return update(cart, response);
    }

    @Override
    public Map<String, Integer> getCountOfItems(Cart cart) {
        Integer quantities = cart.getQuantities();
        Map<String, Integer> map = new HashMap<>();
        map.put("count",quantities);
        return map;
    }

    @Override
    public Map<String, Double> getGrandTotal(Cart cart) {
        Double grandTotal = cart.getGrandTotal();
        Map<String, Double> map = new HashMap<>();
        map.put("grandtotal", grandTotal);
        return map;
    }


    @Override
    public void removeItem(Cart cart, Product product, HttpServletResponse response) throws IOException {

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setTotalPrice(product.getPrice());
        cartItem.setQuantity(1);
        cart.removeCartItem(cartItem);
        update(cart, response);
    }

    @Override
    public void removeAllItem(Cart cart, HttpServletResponse response) throws IOException {
        cart.removeAllCartItem();
        update(cart, response);
    }
}
