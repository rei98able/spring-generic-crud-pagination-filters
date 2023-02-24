package com.example.springgenericcrudpaginationfilters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * DTO for ControllerAdvice ResponseEntity exceptions
 */
@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
