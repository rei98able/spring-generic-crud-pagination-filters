package com.example.springgenericcrudpaginationfilters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleFindAllDTO {
    private Long id;

    private String code;

    private Timestamp dtCreated;

    private Boolean active;

    private Integer count;

    private String nestedObjectCode;
}
