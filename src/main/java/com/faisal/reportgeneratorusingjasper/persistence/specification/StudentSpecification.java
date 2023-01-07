package com.faisal.reportgeneratorusingjasper.persistence.specification;

import com.faisal.reportgeneratorusingjasper.domain.StudentFilter;
import com.faisal.reportgeneratorusingjasper.persistence.entity.StudentEntity;
import com.faisal.reportgeneratorusingjasper.persistence.specification.generic.SpecificationBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class StudentSpecification {
    public static Specification<StudentEntity> apply(StudentFilter filter) {
        Specification<StudentEntity> specification = SpecificationBuilder.conjunction();

        if (StringUtils.isNotBlank(filter.getAdmissionYear())) {
            specification =
                Objects.requireNonNull(specification)
                    .and(
                        SpecificationBuilder.equal("admissionYear", filter.getAdmissionYear()));
        }
        if (StringUtils.isNotBlank(filter.getCurrentSemester())) {
            specification =
                Objects.requireNonNull(specification)
                    .and(SpecificationBuilder.equal("currentSemester", filter.getCurrentSemester()));
        }

        if (StringUtils.isNotBlank(filter.getCurrentSection())) {
            specification =
                Objects.requireNonNull(specification)
                    .and(SpecificationBuilder.equal("currentSection", filter.getCurrentSection()));
        }

        if (StringUtils.isNotBlank(filter.getCurrentCgpa())) {
            specification =
                Objects.requireNonNull(specification)
                    .and(SpecificationBuilder.equal("currentCgpa", filter.getCurrentCgpa()));
        }

        if (Objects.nonNull(filter.getCreditCompleted())) {
            specification =
                Objects.requireNonNull(specification)
                    .and(SpecificationBuilder.greaterThanEqual("creditCompleted", filter.getCreditCompleted()));
        }

        return specification;
    }
}
