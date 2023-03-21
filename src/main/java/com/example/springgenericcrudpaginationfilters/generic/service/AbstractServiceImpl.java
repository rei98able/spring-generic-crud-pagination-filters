package com.example.springgenericcrudpaginationfilters.generic.service;

import com.example.springgenericcrudpaginationfilters.generic.dto.SearchRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import com.example.springgenericcrudpaginationfilters.generic.repository.AbstractRepository;
import com.example.springgenericcrudpaginationfilters.generic.repository.SearchSpecification;
import com.example.springgenericcrudpaginationfilters.web.advice.exception.*;
import com.example.springgenericcrudpaginationfilters.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ManyToMany;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Abstract CRUD Service Implementation
 *
 * @param <E> Any entity that extends AbstractEntity
 * @param <R> Any repository that extents AbstractRepository
 */
@RequiredArgsConstructor
public abstract class AbstractServiceImpl<E extends AbstractEntity, R extends AbstractRepository<E>> implements AbstractService<E> {

    private final R repository;

    @Autowired
    private ModelMapper patchingMapper;


    /**
     * Creates new entity
     *
     * @param entity - entity from request
     * @return entity from database
     */
    @Override
    @Transactional
    public E save(E entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new EntitySaveException("Couldn't save entity: " + entity + "\n" + e.getMessage());
        }
    }

    /**
     * Updates the entity using ModelMapper (not all fields required)
     *
     * @param entity - entity from request
     * @return entity from database
     */
    @Override
    @Transactional
    public E update(E entity) {
        try {
            E entityFromBd = repository.findById(entity.getId()).orElseThrow();
            patchingMapper.map(entity, entityFromBd);
//            handleManyToManyRelationships(entity, entityFromBd);
            return repository.saveAndFlush(entityFromBd);
        } catch (NoSuchElementException e) {
            throw new EntityFindByIdException("Can't find with id: " + entity.getId());
        } catch (Exception e) {
            throw new EntityUpdateException("Couldn't update entity: " + entity + e.getMessage());
        }
    }

    /**
     * Deletes entity by id
     *
     * @param id - id in database
     */
    @Override
    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new EntityFindByIdException("Can't find with id: " + id);
        } catch (Exception e) {
            throw new EntityDeleteException("Couldn't delete with id: " + id);
        }
    }

    /**
     * Find entity by id
     *
     * @param id - id in database
     */
    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityFindByIdException("Can't find with id: " + id));
    }

    /**
     * Gets all entities from database by given pagination and filters
     *
     * @param search - request filters
     * @return Page of entity after filtration
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseDTO<E> findAll(SearchRequestDTO search) {
        try {
            SearchSpecification<E> specification = new SearchSpecification<>(search);
            Pageable pageable = SearchSpecification.getPageable(search.getPage(), search.getSize());
            Page<E> page = repository.findAll(specification, pageable);
            return ResponseDTO
                    .<E>builder()
                    .content(page.getContent())
                    .totalElements(page.getTotalElements())
                    .totalPages(page.getTotalPages())
                    .build();
        } catch (Exception e) {
            throw new EntityFindAllException("Couldn't get page with filters: " + search + "\n" + e.getMessage());
        }
    }

    private void handleManyToManyRelationships(E entityFromRequest, E entityFromDb) {
        Set<Field> manyToManyFields = getManyToManyFields(entityFromDb.getClass());
        for (Field field : manyToManyFields) {
            field.setAccessible(true);
            try {
                Set<E> existingItems = (Set<E>) field.get(entityFromDb);
                Set<E> newItems = (Set<E>) field.get(entityFromRequest);
                existingItems.clear();
                if (newItems != null) {
                    existingItems.addAll(newItems);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error updating many-to-many relationship", e);
            }
        }
    }

    private Set<Field> getManyToManyFields(Class<?> entityClass) {
        Set<Field> manyToManyFields = new HashSet<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ManyToMany.class)) {
                manyToManyFields.add(field);
            }
        }
        if (entityClass.getSuperclass() != null && !entityClass.getSuperclass().equals(Object.class)) {
            manyToManyFields.addAll(getManyToManyFields(entityClass.getSuperclass()));
        }
        return manyToManyFields;
    }
}
