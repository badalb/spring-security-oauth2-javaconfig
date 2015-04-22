package com.test.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
   
	public InvalidCredentialsException(String msg)   {
        super(msg);
    }
    public InvalidCredentialsException(String msg, Exception e)  {
        super(msg, e);
    }
}
