package com.example.springgenericcrudpaginationfilters.generic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class contains fields of request search representation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDTO {
    private List<FilterRequestDTO> filters;

    private List<SortRequestDTO> sorts;

    private Integer page;

    private Integer size;
}
