package com.bootshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue
	private String cartid;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems;
	
	@OneToOne
	@JoinColumn(name="customerId")
	@JsonIgnore
	private Customer customer;
	
	private double grandTotal;
	
	public Cart() {
		cartItems = new ArrayList<CartItem>();
		this.grandTotal=0;
	}

	public Cart(String cartid) {
		this.cartid = cartid;
		cartItems = new ArrayList<CartItem>();
		this.grandTotal=0;
	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	// add item to cart
	@JsonIgnore
	public void addCartItem(CartItem item) {
		if (cartItems.contains(item)) {
			for (CartItem cartItem : cartItems) {
				if (cartItem.equals(item)) {
					cartItem.setQuantity(item.getQuantity()
							+ cartItem.getQuantity());
					cartItem.setTotalPrice(cartItem.getTotalPrice()
							+ item.getTotalPrice());
				}
			}
		} else {
			cartItems.add(item);
		}
		this.setGrandTotal();
	}

	// Remove item from cart
	@JsonIgnore
	public void removeCartItem(CartItem item) {
		if (this.cartItems.contains(item)) {
			for (CartItem cartItem : this.cartItems) {
				if (cartItem.equals(item)) {
						cartItems.remove(item);
				}
				break;
			}
		}
		this.setGrandTotal();
	}

	@JsonIgnore
	public void removeAllCartItem() {
		cartItems.clear();
		this.setGrandTotal();
	}

	@JsonIgnore
	public int getQuantities() {
		int result = 0;
		for (CartItem item : cartItems) {
			result = result + item.getQuantity();
		}
		return result;
	}
	
	public double getGrandTotal() {
		return grandTotal;
	}

	@JsonIgnore
	public void setGrandTotal() {
		double result = 0;
		for (CartItem item : cartItems) {
			result = result + item.getTotalPrice();
		}
		this.grandTotal=result;
	}

	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", cartItems=" + cartItems
				+ ", grandTotal=" + grandTotal + "]";
	}
	
}
