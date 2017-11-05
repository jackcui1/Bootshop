package com.bootshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customerorder")
public class CustomerOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int orderid;
	
	@OneToOne
	@JoinColumn(name="cartid")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="shippingaddressid")
	private ShippingAddress shippingAddress;
	
	public CustomerOrder(){
		customer=new Customer();
		cart=new Cart();
		shippingAddress=new ShippingAddress();
	}
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "CustomerOrder [orderid=" + orderid + ", cart=" + cart
				+ ", customer=" + customer + ", shippingAddress="
				+ shippingAddress + "]";
	}

	
	
	
}
