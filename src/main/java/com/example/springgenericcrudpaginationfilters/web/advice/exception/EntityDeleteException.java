package com.example.springgenericcrudpaginationfilters.web.advice.exception;

import com.example.springgenericcrudpaginationfilters.generic.exception.AbstractException;

public class EntityDeleteException extends AbstractException {
    public EntityDeleteException(String msg) {
        super(msg);
    }
}
