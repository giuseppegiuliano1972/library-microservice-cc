package com.cc.member.exception;

public class MemberServiceCustomException extends RuntimeException{


	private static final long serialVersionUID = -4249828149061008561L;
	private final String errorCode;

    public MemberServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

	public String getErrorCode() {
		return errorCode;
	}
    
}