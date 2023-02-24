package com.example.springgenericcrudpaginationfilters.web.advice.exception;

import com.example.springgenericcrudpaginationfilters.generic.exception.AbstractException;

public class EntityFindByIdException extends AbstractException {
    public EntityFindByIdException(String msg) {
        super(msg);
    }
}
