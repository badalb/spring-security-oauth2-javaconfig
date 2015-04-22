package com.test.service.exceptions;
import java.io.Serializable;
 
public class RequestParamParseException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public RequestParamParseException(String msg)   {
        super(msg);
    }
    public RequestParamParseException(String msg, Exception e)  {
        super(msg, e);
    }
}