package com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentFilter;
import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StudentSpecification implements Specification<StudentEntity> {

  private List<SearchCriteria> list;

  public StudentSpecification() {
    this.list = new ArrayList<>();
  }

  public void addSearchCriteria(SearchCriteria criteria){
    list.add(criteria);
  }

  //
  public void applyFilter(StudentFilter filter){
    if(filter.getAdmissionYear()!=null)
      addSearchCriteria(new SearchCriteria("admissionYear",filter.getAdmissionYear(),SearchOperation.EQUAL));
    if (filter.getCurrentSemester() != null)
      addSearchCriteria(new SearchCriteria("currentSemester",filter.getCurrentSemester(),SearchOperation.EQUAL));
    if (filter.getCurrentSection() != null)
      addSearchCriteria(new SearchCriteria("currentSection",filter.getCurrentSection(),SearchOperation.EQUAL));
    if (filter.getCreditCompleted() != null)
      addSearchCriteria(new SearchCriteria("creditCompleted",filter.getCreditCompleted(),SearchOperation.GREATER_THAN_EQUAL));
  }

  @Override
  public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
    var predicates = new ArrayList<Predicate>();

    for (SearchCriteria criteria: list) {
      if(criteria.getOperation().equals(SearchOperation.GREATER_THAN)){
        predicates.add(builder.greaterThan(root.get(criteria.getKey()),criteria.getValue().toString()));
      }
      else if(criteria.getOperation().equals(SearchOperation.LESS_THAN)){
        predicates.add(builder.lessThan(root.get(criteria.getKey()),criteria.getValue().toString()));
      }
      else if(criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)){
        predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()),criteria.getValue().toString()));
      }
      else if(criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)){
        predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()),criteria.getValue().toString()));
      }
      else if(criteria.getOperation().equals(SearchOperation.EQUAL)){
        predicates.add(builder.equal(root.get(criteria.getKey()),criteria.getValue().toString()));
      }
      else if(criteria.getOperation().equals(SearchOperation.NOT_EQUAL)){
        predicates.add(builder.notEqual(root.get(criteria.getKey()),criteria.getValue().toString()));
      }
      else if(criteria.getOperation().equals(SearchOperation.NOT_NULL)){
        predicates.add(builder.isNotNull(root.get(criteria.getKey())));
      }
      else if(criteria.getOperation().equals(SearchOperation.NULL)){
        predicates.add(builder.isNull(root.get(criteria.getKey())));
      }
    }
    return builder.and(predicates.toArray(new Predicate[0]));
  }
}
