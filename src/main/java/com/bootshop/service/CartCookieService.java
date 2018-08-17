package com.bootshop.service;

import com.bootshop.model.Cart;
import com.bootshop.model.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author Guowei Cui
 * @date 8/16/2018 2:04 PM
 */
public interface CartCookieService {

    Cart createOrGet(HttpServletRequest request, HttpServletResponse reponse) throws IOException;

    Cart update(Cart cart, HttpServletResponse response) throws IOException;

    Cart addItem(Cart cart, Product product,  HttpServletResponse response) throws IOException;

    Map<String, Integer> getCountOfItems(Cart cart);

    Map<String, Double> getGrandTotal(Cart cart);

    void removeItem(Cart cart, Product product, HttpServletResponse response) throws IOException;

    void removeAllItem(Cart cart, HttpServletResponse response) throws IOException;

}
