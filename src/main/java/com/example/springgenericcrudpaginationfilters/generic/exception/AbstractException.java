package com.example.springgenericcrudpaginationfilters.generic.exception;

/**
 * Abstract Exception for Controller Advice
 */
public class AbstractException extends RuntimeException {
    public AbstractException(String msg) {
        super(msg);
    }
}

