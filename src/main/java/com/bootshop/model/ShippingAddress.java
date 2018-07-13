package com.bootshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="shippingaddress")
public class ShippingAddress implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int shippingaddressid;
	
	private String streetName;
	private String apartmentNumber;
	private String city;
	private String country;
	private String ZipCode;
	
	public int getShippingaddressid() {
		return shippingaddressid;
	}
	public void setShippingaddressid(int shippingaddressid) {
		this.shippingaddressid = shippingaddressid;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	@Override
	public String toString() {
		return "ShippingAddress [shippingaddressid=" + shippingaddressid
				+ ", streetName=" + streetName + ", apartmentNumber="
				+ apartmentNumber + ", city=" + city + ", country=" + country
				+ ", ZipCode=" + ZipCode + "]";
	}
	
	
	
}
