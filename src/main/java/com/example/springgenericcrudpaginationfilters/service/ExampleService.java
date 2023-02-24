package com.example.springgenericcrudpaginationfilters.service;

import com.example.springgenericcrudpaginationfilters.generic.service.AbstractServiceImpl;
import com.example.springgenericcrudpaginationfilters.model.ExampleEntity;
import com.example.springgenericcrudpaginationfilters.repository.ExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class ExampleService extends AbstractServiceImpl<ExampleEntity, ExampleRepository> {
    public ExampleService(ExampleRepository repository) {
        super(repository);
    }
}
