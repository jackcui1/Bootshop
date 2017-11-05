package com.bootshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int customerid;
	
	@NotEmpty(message="The customer name must not be Null.")
	private String customername;
	
	@NotEmpty(message="The Email address must not be Null.")
	private String email;
	
	private String phone;
	
	@OneToOne
	@JoinColumn(name="shippingaddressid")
	private ShippingAddress shippingAddress;
	
	@OneToOne
	@JoinColumn(name="cartid")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name="userid")
	private User user;
	
	
	public Customer(int customerid, String customername, String email,
			String phone, ShippingAddress shippingAddress, Cart cart, User user) {
		super();
		this.customerid = customerid;
		this.customername = customername;
		this.email = email;
		this.phone = phone;
		this.shippingAddress = shippingAddress;
		this.cart = cart;
		this.user = user;
	}
	
	public Customer(){
		this.cart = new Cart();
		this.shippingAddress=new ShippingAddress();
		this.user=new User();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername="
				+ customername + ", email=" + email + ", phone=" + phone
				+ ", shippingAddress=" + shippingAddress + ", cart=" + cart
				+ ", user=" + user + "]";
	}
	
	
}
