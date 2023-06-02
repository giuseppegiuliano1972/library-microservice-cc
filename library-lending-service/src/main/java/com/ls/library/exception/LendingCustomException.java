package com.ls.library.exception;

public class LendingCustomException extends RuntimeException{

	private static final long serialVersionUID = 4958451313521861913L;
	private String errorCode;
    private int status;

    public LendingCustomException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    

}
