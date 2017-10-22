package com.customexceptions;


/*
 * This is Custom Exception class used in CSV file reader
 */
public class CustomException extends Exception
{
	public CustomException(){
	}

	public CustomException(String message){
		super(message);
	}

	public CustomException(Throwable cause){
		super(cause);
	}

	public CustomException(String message, Throwable cause){
		super(message, cause);
	}

	public CustomException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
