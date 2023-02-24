package com.example.springgenericcrudpaginationfilters.web.advice.exception;

import com.example.springgenericcrudpaginationfilters.generic.exception.AbstractException;

public class EntitySaveException extends AbstractException {
    public EntitySaveException(String msg) {
        super(msg);
    }
}
