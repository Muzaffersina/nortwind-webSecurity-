package com.turkcell.northwind.core.utilitys.results;

public class Result {
	private boolean success;
	private String message;
	
	public Result(boolean success) {		// sadece başarı durumu
		this.success = success;
	}


	public Result(boolean success, String message) {   // mesaj ve başarı durumu
		this(success);								
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public String getMessage() {
		return message;
	}
		
}
