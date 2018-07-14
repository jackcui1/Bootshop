package com.bootshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cartitem")
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	//@JsonIgnore
	private int cartitemid;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "cartid")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	private int quantity;

	@Column(name = "totalprice")
	private double totalPrice;

	public int getCartitemid() {
		return cartitemid;
	}

	public void setCartitemid(int cartitemid) {
		this.cartitemid = cartitemid;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	@JsonIgnore
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.product.getProductname().hashCode();
		result=31*result+this.product.getProductid();
		result=31*result+this.product.getBrand().hashCode();
		return result;
	}

	@Override
	@JsonIgnore
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		CartItem other = (CartItem) obj;
		return other.product.getProductid() == this.product.getProductid()
				|| (other.product.getProductname().equals(this.product.getProductname())
						&& other.product.getProductname() != null && this.product.getProductname() != null);
	}

	@Override
	public String toString() {
		return "CartItem [cartitemid=" + cartitemid + ", cart=" + cart
				+ ", product=" + product + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}
	
}
