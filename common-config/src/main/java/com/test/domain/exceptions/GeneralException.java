package com.test.domain.exceptions;
import java.io.Serializable;
 
public class GeneralException extends Exception implements Serializable
{
    private static final long serialVersionUID = 1L;

    public GeneralException(String msg)   {
        super(msg);
    }
    public GeneralException(String msg, Exception e)  {
        super(msg, e);
    }
}