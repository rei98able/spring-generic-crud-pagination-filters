package com.example.springgenericcrudpaginationfilters.generic.dto.enums;

import com.example.springgenericcrudpaginationfilters.generic.dto.FilterRequestDTO;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This enum represents operator of searching entity e.g. table
 * By default LIKE operator can be used to filter tables
 */
@Slf4j
public enum Operator {

    /**
     * Filter when given value is exactly the same
     */
    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate, Path<Object> path) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            return cb.and(cb.equal(path.as(value.getClass()), value), predicate);
        }
    },

    /**
     * Filter when given value is not the same
     */
    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate, Path<Object> path) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            return cb.and(cb.notEqual(path.as(value.getClass()), value), predicate);
        }
    },

    /**
     * Filter when given value is in the text
     */
    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate, Path<Object> path) {
            return cb.and(cb.like(path.as(String.class), "%" + request.getValue().toString() + "%"), predicate);
        }
    },

    /**
     * Filter when given value is in array of values
     */
    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate, Path<Object> path) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(path.as(values.get(0).getClass()));
            for (Object value : values) {
                inClause.value(request.getFieldType().parse(value.toString()));
            }
            return cb.and(inClause, predicate);
        }
    },

    /**
     * Filter when given value is in between of values
     */
    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate, Path<Object> path) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate, Path<Object> path);

}