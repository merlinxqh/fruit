package com.fruit.base.framework.exception;

/**
 * Service异常
 */
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -8396020297026661487L;
	
	private int code;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(int code) {
        super();
        this.code = code;
    }
	
	public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
	
}
