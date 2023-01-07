package com.faisal.reportgeneratorusingjasper.persistence.repository;

import com.faisal.reportgeneratorusingjasper.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity> {

    boolean existsByStudentId(String id);
}
