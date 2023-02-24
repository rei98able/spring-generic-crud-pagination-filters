package com.example.springgenericcrudpaginationfilters.generic.controller;

import com.example.springgenericcrudpaginationfilters.generic.dto.SearchRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import com.example.springgenericcrudpaginationfilters.generic.service.AbstractService;
import com.example.springgenericcrudpaginationfilters.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Abstract CRUD Controller Implementation
 *
 * @param <E> Any entity that extends AbstractEntity
 * @param <S> Any service that extends AbstractService
 */
@RequiredArgsConstructor
public abstract class AbstractControllerImpl<E extends AbstractEntity, S extends AbstractService<E>>
        implements AbstractController<E> {

    private final S service;

    /**
     * Gets Page of entity by filter, sort, page number, size
     *
     * @param search - filter request
     * @return Page of generic entity
     */
    @Override
    public ResponseEntity<ResponseDTO<E>> getPage(@RequestBody SearchRequestDTO search) {
        return ResponseEntity.ok(service.findAll(search));
    }

    /**
     * Gets an entity by id
     *
     * @param id - id in database
     * @return entity
     */
    @Override
    public ResponseEntity<E> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Updates an entity by given entity from request (not all fields are required)
     *
     * @param update - Entity to update
     * @return saved entity from database
     */
    @Override
    public ResponseEntity<E> update(@RequestBody E update) {
        return ResponseEntity.ok(service.update(update));
    }

    /**
     * Creates an entity by given entity from request
     *
     * @param create - Entity to create
     * @return saved entity from database
     */
    @Override
    public ResponseEntity<E> create(@RequestBody E create) {
        return ResponseEntity.ok(service.save(create));
    }

    /**
     * Deletes an entity by id
     *
     * @param id - Id from database
     */
    @Override
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}