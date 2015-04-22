package com.test.domain.exceptions;

public class UserNotActiveException extends RuntimeException{
	private static final long serialVersionUID = 1L;
    
	public UserNotActiveException(String msg)   {
        super(msg);
    }
    public UserNotActiveException(String msg, Exception e)  {
        super(msg, e);
    }
}
