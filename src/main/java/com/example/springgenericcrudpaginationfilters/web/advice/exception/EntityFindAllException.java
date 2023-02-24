package com.example.springgenericcrudpaginationfilters.web.advice.exception;

import com.example.springgenericcrudpaginationfilters.generic.exception.AbstractException;

public class EntityFindAllException extends AbstractException {
    public EntityFindAllException(String msg) {
        super(msg);
    }
}
