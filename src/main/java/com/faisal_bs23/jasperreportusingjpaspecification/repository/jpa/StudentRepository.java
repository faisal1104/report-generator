package com.faisal_bs23.jasperreportusingjpaspecification.repository.jpa;

import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity> {

  boolean existsByStudentId(String id);
}
