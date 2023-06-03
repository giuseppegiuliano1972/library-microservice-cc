package com.la.auth.exception;

public class AuthCustomException extends RuntimeException{

	private static final long serialVersionUID = 4958451313521861913L;
	private final String errorCode;

    public AuthCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

	public String getErrorCode() {
		return errorCode;
	}
    
    

}
