package com.example.springgenericcrudpaginationfilters.generic.controller;

import com.example.springgenericcrudpaginationfilters.generic.dto.SearchRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import com.example.springgenericcrudpaginationfilters.web.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Abstract Controller for defining swagger endpoints description
 *
 * @param <E> Any entity that extends AbstractEntity
 */
public interface AbstractController<E extends AbstractEntity> {

    @Operation(summary = "Find all by page and filters")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/page")
    ResponseEntity<ResponseDTO<E>> getPage(@RequestBody SearchRequestDTO search);

    @Operation(summary = "Find by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    ResponseEntity<E> getById(@PathVariable Long id);

    @Operation(summary = "Update")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    ResponseEntity<E> update(@RequestBody E update);

    @Operation(summary = "Create")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ResponseEntity<E> create(@RequestBody E create);

    @Operation(summary = "Delete")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id);
}
