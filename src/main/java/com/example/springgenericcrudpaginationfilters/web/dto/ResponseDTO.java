package com.example.springgenericcrudpaginationfilters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ResponseDTO for Pageable
 *
 * @param <E> any entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<E> {
    private List<E> content;
    private Long totalElements;
    private Integer totalPages;
}
