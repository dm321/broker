package com.dm.broker.Exceptions;

public class EmptyStockException extends Exception {

	public EmptyStockException() {
		super("Stock returned null");
		
	}
	

}
