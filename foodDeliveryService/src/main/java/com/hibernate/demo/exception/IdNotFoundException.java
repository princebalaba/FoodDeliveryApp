package com.hibernate.demo.exception;

public class IdNotFoundException extends RuntimeException{
	public IdNotFoundException(String msg) {
		super(msg);
		
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}
