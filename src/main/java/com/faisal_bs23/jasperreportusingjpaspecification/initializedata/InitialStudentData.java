package com.faisal_bs23.jasperreportusingjpaspecification.initializedata;

import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpa.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialStudentData implements CommandLineRunner {

  private final StudentRepository studentRepository;

  public InitialStudentData(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    var student = new StudentEntity[100];

    student[1] = new StudentEntity("C161001","AShraful", "2016", "8", "A" , "3.5", 150 );
    student[2] = new StudentEntity("C161002","Shojib", "2016", "8", "A" , "3.4", 155 );
    student[3] = new StudentEntity("C161003","Rifat", "2016", "8", "A" , "3.3", 150 );
    student[4] = new StudentEntity("C161004","Mahim", "2016", "8", "B" , "3.5", 150 );
    student[5] = new StudentEntity("C161005","Yasar", "2016", "8", "B" , "3.6", 155 );
    student[6] = new StudentEntity("C161006","Tosif", "2016", "8", "B" , "3.7", 155 );
    student[7] = new StudentEntity("C161007","Tanim", "2016", "8", "B" , "3.5", 150 );
    student[8] = new StudentEntity("C161008","Faisal", "2016", "8", "C" , "3.7", 150 );
    student[9] = new StudentEntity("C161009","Raghib", "2016", "8", "C" , "3.6", 155 );
    student[10] = new StudentEntity("C161010","Nishad", "2016", "8", "C" , "3.7", 150 );

    student[11] = new StudentEntity("C171001","Shovon", "2016", "7", "A" , "3.7", 140 );
    student[12] = new StudentEntity("C171002","Ehtesham", "2016", "7", "A" , "3.5", 145 );
    student[13] = new StudentEntity("C171003","Shahadat", "2016", "7", "A" , "3.6", 140 );
    student[14] = new StudentEntity("C171004","Abir", "2016", "7", "A" , "3.3", 140 );
    student[15] = new StudentEntity("C171005","Junayed", "2016", "7", "B" , "3.4", 145 );
    student[16] = new StudentEntity("C171006","Nayeem", "2016", "7", "B" , "3.7", 140 );
    student[17] = new StudentEntity("C171007","Saad", "2016", "7", "B" , "3.6", 140 );
    student[18] = new StudentEntity("C171008","Warid", "2016", "7", "C" , "3.7", 140 );
    student[19] = new StudentEntity("C171009","Piyash", "2016", "7", "C" , "3.8", 145 );
    student[20] = new StudentEntity("C171010","Asnis", "2016", "7", "C" , "3.2", 145 );

    student[21] = new StudentEntity("C181001","Shihab", "2017", "6", "A" , "3.3", 125 );
    student[22] = new StudentEntity("C181002","Shahin", "2017", "6", "A" , "3.4", 125 );
    student[24] = new StudentEntity("C181003","Akash", "2017", "6", "B" , "3.4",120 );
    student[23] = new StudentEntity("C181004","Saif", "2017", "6", "B" , "3.5", 120 );
    student[25] = new StudentEntity("C181005","Ishmam", "2017", "6", "C" , "3.3", 120 );
    student[26] = new StudentEntity("C181006","Kais", "2017", "6", "C" , "3.7", 125 );
    student[27] = new StudentEntity("C181007","Arif", "2017", "6", "C" , "3.6", 120 );
    student[28] = new StudentEntity("C181008","Ibnul", "2017", "6", "C" , "3.5", 120 );
    student[29] = new StudentEntity("C181009","Salahuddin", "2017", "6", "C" , "3.8", 120 );
    student[30] = new StudentEntity("C181010","Arafat", "2017", "6", "C" , "3.3", 120 );

    student[31] = new StudentEntity("C191001","Alif1", "2017", "5", "A" , "3.3", 110 );
    student[32] = new StudentEntity("C191002","Alif2", "2017", "5", "B" , "3.2", 110 );
    student[33] = new StudentEntity("C191003","Alif3", "2017", "5", "A" , "3.4", 115 );
    student[34] = new StudentEntity("C191004","Alif4", "2017", "5", "A" , "3.6", 115 );
    student[35] = new StudentEntity("C191005","Alif5", "2017", "5", "B" , "3.4", 110 );
    student[36] = new StudentEntity("C191006","Alif6", "2017", "5", "C" , "3.5", 115 );
    student[37] = new StudentEntity("C191007","Alif7", "2017", "5", "A" , "3.6", 110 );
    student[38] = new StudentEntity("C191008","Alif8", "2017", "5", "B" , "3.3", 110 );
    student[39] = new StudentEntity("C191009","Alif9", "2017", "5", "A" , "3.4", 110 );
    student[40] = new StudentEntity("C191010","Alif10", "2017", "5", "C" , "3.5", 115 );

    student[41] = new StudentEntity("C201001","Adib1", "2016", "4", "B" , "3.3", 100);
    student[42] = new StudentEntity("C201002","Adib2", "2016", "4", "A" , "3.3", 105);
    student[43] = new StudentEntity("C201003","Adib3", "2016", "4", "C" , "3.6", 105);
    student[44] = new StudentEntity("C201004","Adib4", "2016", "4", "B" , "3.2", 100);
    student[45] = new StudentEntity("C201005","Adib5", "2016", "4", "A" , "3.7", 105);
    student[46] = new StudentEntity("C201006","Adib6", "2016", "4", "C" , "3.2", 100);
    student[47] = new StudentEntity("C201007","Adib7", "2016", "4", "A" , "3.3", 100);
    student[48] = new StudentEntity("C201008","Adib8", "2016", "4", "A" , "3.4", 105);
    student[49] = new StudentEntity("C201009","Adib9", "2016", "4", "B" , "3.5", 100);
    student[50] = new StudentEntity("C201010","Adib10", "2016", "4", "C" , "3.6", 105);

    student[51] = new StudentEntity("C211001","Mashrafi1", "2016", "3", "B" , "3.3", 90);
    student[52] = new StudentEntity("C211002","Mashrafi2", "2016", "3", "B" , "3.3", 95);
    student[53] = new StudentEntity("C211003","Mashrafi3", "2016", "3", "B" , "3.6", 90);
    student[54] = new StudentEntity("C211004","Mashrafi4", "2016", "3", "B" , "3.2", 95);
    student[55] = new StudentEntity("C211005","Mashrafi5", "2016", "3", "B" , "3.7", 90);
    student[56] = new StudentEntity("C211006","Mashrafi6", "2016", "3", "B" , "3.2", 95);
    student[57] = new StudentEntity("C211007","Mashrafi7", "2016", "3", "B" , "3.3", 95);
    student[58] = new StudentEntity("C211008","Mashrafi8", "2016", "3", "B" , "3.4", 90);
    student[59] = new StudentEntity("C211009","Mashrafi9", "2016", "3", "B" , "3.5", 90);
    student[60] = new StudentEntity("C211010","Mashrafi10", "2016", "3", "B" , "3.5", 95);

    for (int i=1;i<=60;i++){
      var s = student[i];

      if(!studentRepository.existsByStudentId(s.getStudentId()))
        studentRepository.save(s);
      else
        System.out.println("Student with id :"+s.getStudentId()+" alradey exist.");
    }
  }
}
