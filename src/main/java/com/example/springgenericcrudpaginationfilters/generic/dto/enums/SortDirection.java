package com.example.springgenericcrudpaginationfilters.generic.dto.enums;

import com.example.springgenericcrudpaginationfilters.generic.dto.SortRequestDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * This enum represents sort direction of filtered entity
 */
public enum SortDirection {

    /**
     * Order by key ascending
     */
    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDTO request) {
            return cb.asc(root.get(request.getKey()));
        }
    },

    /**
     * Order by key descending
     */
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDTO request) {
            return cb.desc(root.get(request.getKey()));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDTO request);

}
