package com.example.springgenericcrudpaginationfilters.generic.service;

import com.example.springgenericcrudpaginationfilters.generic.dto.SearchRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import com.example.springgenericcrudpaginationfilters.web.dto.ResponseDTO;

public interface AbstractService<T extends AbstractEntity> {
    T save(T entity);

    T update(T entity);

    void delete(Long id);

    T findById(Long id);

    ResponseDTO<T> findAll(SearchRequestDTO searchRequestDTO);

}