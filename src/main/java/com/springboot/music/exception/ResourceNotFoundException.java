package com.springboot.music.exception;


import org.springframework.http.HttpStatus;



public class ResourceNotFoundException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public ResourceNotFoundException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public ResourceNotFoundException(String message,HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
