package com.tech.challenge.exception;

public class EntityFoundException extends RuntimeException{
    public EntityFoundException(String message){
        super(message);
    }
}
