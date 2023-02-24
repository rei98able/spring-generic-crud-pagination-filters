package com.example.springgenericcrudpaginationfilters.generic.repository;

import com.example.springgenericcrudpaginationfilters.generic.dto.FilterRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.dto.SearchRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.dto.SortRequestDTO;
import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Generic Specification based on SearchRequestDTO
 *
 * @param <T> - Any entity that extends AbstractEntity
 */
@Slf4j
@AllArgsConstructor
public class SearchSpecification<T extends AbstractEntity> implements Specification<T> {

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_ITEMS_SIZE = 100;
    private final SearchRequestDTO request;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);
        List<Order> orders = new ArrayList<>();

        for (FilterRequestDTO filter : this.request.getFilters()) {
            log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }


        for (SortRequestDTO sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    /**
     * Gets PageRequest of given page and size or defaults
     *
     * @param page - data page number
     * @param size - data size
     * @return Pageable
     */
    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, DEFAULT_PAGE_NUMBER), Objects.requireNonNullElse(size, DEFAULT_ITEMS_SIZE));
    }

}
