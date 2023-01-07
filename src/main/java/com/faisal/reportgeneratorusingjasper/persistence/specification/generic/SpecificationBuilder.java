package com.faisal.reportgeneratorusingjasper.persistence.specification.generic;

import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.List;

public class SpecificationBuilder {
    public static <T> Specification<T> conjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static <T> Specification<T> equal(String key, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(key), value);
    }

    public static <T> Specification<T> notEqual(String key, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(key), value);
    }

    public static <T> Specification<T> greaterThan(String key, Number value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get(key), value);
    }

    public static <T> Specification<T> greaterThanEqual(String key, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(key), value.toString());
    }

    public static <T> Specification<T> lessThanEqual(String key, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(key), value.toString());
    }

    public static <T> Specification<T> dateTimeGreaterThanEqual(String key, Object value) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get(key), (OffsetDateTime) value);
    }

    public static <T> Specification<T> dateTimeLessThanEqual(String key, Object value) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(root.get(key), (OffsetDateTime) value);
    }

    public static <T> Specification<T> lessThan(String key, Number value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lt(root.get(key), value);
    }

    public static <T> Specification<T> notNull(String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(key));
    }

    public static <T> Specification<T> like(String key, String value) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(criteriaBuilder.upper(root.get(key)), "%" + value.toUpperCase() + "%");
    }

    public static <T> Specification<T> in(String key, List<?> values) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(key)).value(values);
    }

    public static <T> Specification<T> equal(String parentKey, String childKey, Object value) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get(parentKey).get(childKey), value);
    }

    public static <T> Specification<T> isNull(String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(key));
    }
}
