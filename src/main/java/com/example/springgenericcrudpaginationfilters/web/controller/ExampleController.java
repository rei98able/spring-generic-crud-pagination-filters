package com.example.springgenericcrudpaginationfilters.web.controller;

import com.example.springgenericcrudpaginationfilters.generic.controller.AbstractControllerImpl;
import com.example.springgenericcrudpaginationfilters.model.ExampleEntity;
import com.example.springgenericcrudpaginationfilters.service.ExampleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/example")
@Tag(name = "example-entity", description = "Example")
public class ExampleController extends AbstractControllerImpl<ExampleEntity, ExampleService> {
    public ExampleController(ExampleService service) {
        super(service);
    }
}
