package com.cc.book.exception;

public class BookCustomException extends RuntimeException {


	private static final long serialVersionUID = 7404799652763818924L;
	private String errorCode;

    public BookCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
