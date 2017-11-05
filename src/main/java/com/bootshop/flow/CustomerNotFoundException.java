package com.bootshop.flow;

public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
