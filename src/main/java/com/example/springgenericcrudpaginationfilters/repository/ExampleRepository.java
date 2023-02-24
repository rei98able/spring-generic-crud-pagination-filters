package com.example.springgenericcrudpaginationfilters.repository;

import com.example.springgenericcrudpaginationfilters.generic.repository.AbstractRepository;
import com.example.springgenericcrudpaginationfilters.model.ExampleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends AbstractRepository<ExampleEntity> {
}
