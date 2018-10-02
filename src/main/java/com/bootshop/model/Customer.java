package com.bootshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Data
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

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername="
				+ customername + ", email=" + email + ", phone=" + phone
				+ ", shippingAddress=" + shippingAddress + ", cart=" + cart
				+ ", user=" + user + "]";
	}
	
	
}
