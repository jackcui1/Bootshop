package com.bootshop.controller.restapi;

import com.bootshop.model.Cart;
import com.bootshop.model.Product;
import com.bootshop.service.CartCookieService;
import com.bootshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Guowei Cui
 * @date 8/16/2018 3:13 PM
 */


@RestController
@RequestMapping("/rest/carts")
public class CartRestCookieController {

    @Autowired
    private CartCookieService cartCookieService;

    @Autowired
    private ProductService productService;

    @RequestMapping("")
    public Cart getCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = cartCookieService.createOrGet(request, response);
        return cart;
    }

    @RequestMapping(value = "/add/{productid}", method = RequestMethod.GET)
    public Cart addItem(@PathVariable("productid") int productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productService.getProductById(productId);
        Cart cart = cartCookieService.createOrGet(request, response);
        return cartCookieService.addItem(cart, product, response);
    }

    @RequestMapping("/count")
    public Map<String, Integer> getCount(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cart cart = cartCookieService.createOrGet(request, response);
        return cartCookieService.getCountOfItems(cart);
    }


    @RequestMapping("/grandtotal")
    public Map<String, Double> getGrandtotal(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cart cart = cartCookieService.createOrGet(request, response);
        return cartCookieService.getGrandTotal(cart);
    }

    @RequestMapping(value = "/remove/{productid}", method = RequestMethod.GET)
    public void removeItem(@PathVariable("productid") int productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = cartCookieService.createOrGet(request, response);
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartCookieService.removeItem(cart, product, response);
        }
    }

    @RequestMapping("/removeall/")
    public void removeAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cart cart = cartCookieService.createOrGet(request, response);
        cartCookieService.removeAllItem(cart, response);
    }


}

