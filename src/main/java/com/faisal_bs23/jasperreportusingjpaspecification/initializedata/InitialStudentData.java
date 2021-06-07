package com.faisal_bs23.jasperreportusingjpaspecification.initializedata;

import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpa.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class InitialStudentData implements CommandLineRunner {

  private final StudentRepository studentRepository;

  public InitialStudentData(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    var student1 = new StudentEntity("C161001","AShraful", "2016", "8", "A" , "3.5", "150" );
    var student2 = new StudentEntity("C161002","Shojib", "2016", "8", "A" , "3.4", "150" );
    var student3 = new StudentEntity("C161003","Rifat", "2016", "8", "A" , "3.3", "150" );
    var student4 = new StudentEntity("C161004","Mahim", "2016", "8", "B" , "3.5", "150" );
    var student5 = new StudentEntity("C161005","Yasar", "2016", "8", "B" , "3.6", "150" );
    var student6 = new StudentEntity("C161006","Tosif", "2016", "8", "B" , "3.7", "150" );
    var student7 = new StudentEntity("C161007","Tanim", "2016", "8", "B" , "3.5", "150" );
    var student8 = new StudentEntity("C161008","Faisal", "2016", "8", "C" , "3.7", "150" );
    var student9 = new StudentEntity("C161009","Raghib", "2016", "8", "C" , "3.6", "150" );
    var student10 = new StudentEntity("C161010","Nishad", "2016", "8", "C" , "3.7", "150" );

    var student11 = new StudentEntity("C171001","Shovon", "2017", "7", "A" , "3.7", "140" );
    var student12 = new StudentEntity("C171002","Ehtesham", "2017", "7", "A" , "3.5", "140" );
    var student13 = new StudentEntity("C171003","Shahadat", "2017", "7", "A" , "3.6", "140" );
    var student14 = new StudentEntity("C171004","Abir", "2017", "7", "A" , "3.3", "140" );
    var student15 = new StudentEntity("C171005","Junayed", "2017", "7", "B" , "3.4", "140" );
    var student16 = new StudentEntity("C171006","Nayeem", "2017", "7", "B" , "3.7", "140" );
    var student17 = new StudentEntity("C171007","Saad", "2017", "7", "B" , "3.6", "140" );
    var student18 = new StudentEntity("C171008","Warid", "2017", "7", "C" , "3.7", "140" );
    var student19 = new StudentEntity("C171009","Piyash", "2017", "7", "C" , "3.8", "140" );
    var student20 = new StudentEntity("C171010","Asnis", "2017", "7", "C" , "3.2", "140" );

    var student21 = new StudentEntity("C181001","Shihab", "2018", "6", "A" , "3.3", "120" );
    var student22 = new StudentEntity("C181002","Shahin", "2018", "6", "A" , "3.4", "120" );
    var student24 = new StudentEntity("C181003","Akash", "2018", "6", "B" , "3.4", "120" );
    var student23 = new StudentEntity("C181004","Saif", "2018", "6", "B" , "3.5", "120" );
    var student25 = new StudentEntity("C181005","Ishmam", "2018", "6", "C" , "3.3", "120" );
    var student26 = new StudentEntity("C181006","Kais", "2018", "6", "C" , "3.7", "120" );
    var student27 = new StudentEntity("C181007","Arif", "2018", "6", "C" , "3.6", "120" );
    var student28 = new StudentEntity("C181008","Ibnul", "2018", "6", "C" , "3.5", "120" );
    var student29 = new StudentEntity("C181009","Salahuddin", "2018", "6", "C" , "3.8", "120" );
    var student30 = new StudentEntity("C181010","Arafat", "2018", "6", "C" , "3.3", "120" );

    Stream.of(student1,student2, student3, student4, student5, student6, student7, student8, student9, student10, student11, student12, student13, student14, student15, student16, student17, student18, student19, student20, student21, student22, student24, student23, student25, student26, student27, student28, student29, student30)
        .filter(s -> !studentRepository.existsByStudentId(s.getStudentId()))
        .map(studentRepository::save)
        .count();
  }
}
