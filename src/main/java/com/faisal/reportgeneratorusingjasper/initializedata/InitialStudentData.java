package com.faisal.reportgeneratorusingjasper.initializedata;

import com.faisal.reportgeneratorusingjasper.persistence.entity.StudentEntity;
import com.faisal.reportgeneratorusingjasper.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitialStudentData implements CommandLineRunner {

  private final StudentRepository studentRepository;

  @Override
  public void run(String... args) throws Exception {
    var students = new StudentEntity[81];

    students[0] = new StudentEntity("C161000","CR7", "2016", "8", "A" , "3.5", 150 );
    students[1] = new StudentEntity("C161001","AShraful", "2016", "8", "A" , "3.5", 150 );
    students[2] = new StudentEntity("C161002","Shojib", "2016", "8", "A" , "3.4", 155 );
    students[3] = new StudentEntity("C161003","Rifat", "2016", "8", "A" , "3.3", 150 );
    students[4] = new StudentEntity("C161004","Mahim", "2016", "8", "B" , "3.5", 150 );
    students[5] = new StudentEntity("C161005","Yasar", "2016", "8", "B" , "3.6", 155 );
    students[6] = new StudentEntity("C161006","Tosif", "2016", "8", "B" , "3.7", 155 );
    students[7] = new StudentEntity("C161007","Tanim", "2016", "8", "B" , "3.5", 150 );
    students[8] = new StudentEntity("C161008","Faisal", "2016", "8", "C" , "3.7", 150 );
    students[9] = new StudentEntity("C161009","Raghib", "2016", "8", "C" , "3.6", 155 );
    students[10] = new StudentEntity("C161010","Nishad", "2016", "8", "C" , "3.7", 150 );

    students[11] = new StudentEntity("C171001","Shovon", "2016", "7", "A" , "3.7", 140 );
    students[12] = new StudentEntity("C171002","Ehtesham", "2016", "7", "A" , "3.5", 145 );
    students[13] = new StudentEntity("C171003","Shahadat", "2016", "7", "A" , "3.6", 140 );
    students[14] = new StudentEntity("C171004","Abir", "2016", "7", "A" , "3.3", 140 );
    students[15] = new StudentEntity("C171005","Junayed", "2016", "7", "B" , "3.4", 145 );
    students[16] = new StudentEntity("C171006","Nayeem", "2016", "7", "B" , "3.7", 140 );
    students[17] = new StudentEntity("C171007","Saad", "2016", "7", "B" , "3.6", 140 );
    students[18] = new StudentEntity("C171008","Warid", "2016", "7", "C" , "3.7", 140 );
    students[19] = new StudentEntity("C171009","Piyash", "2016", "7", "C" , "3.8", 145 );
    students[20] = new StudentEntity("C171010","Asnis", "2016", "7", "C" , "3.2", 145 );

    students[21] = new StudentEntity("C181001","Shihab", "2017", "6", "A" , "3.3", 125 );
    students[22] = new StudentEntity("C181002","Shahin", "2017", "6", "A" , "3.4", 125 );
    students[24] = new StudentEntity("C181003","Akash", "2017", "6", "B" , "3.4",120 );
    students[23] = new StudentEntity("C181004","Saif", "2017", "6", "B" , "3.5", 120 );
    students[25] = new StudentEntity("C181005","Ishmam", "2017", "6", "C" , "3.3", 120 );
    students[26] = new StudentEntity("C181006","Kais", "2017", "6", "C" , "3.7", 125 );
    students[27] = new StudentEntity("C181007","Arif", "2017", "6", "C" , "3.6", 120 );
    students[28] = new StudentEntity("C181008","Ibnul", "2017", "6", "C" , "3.5", 120 );
    students[29] = new StudentEntity("C181009","Salahuddin", "2017", "6", "C" , "3.8", 120 );
    students[30] = new StudentEntity("C181010","Arafat", "2017", "6", "C" , "3.3", 120 );

    students[31] = new StudentEntity("C191001","Siyam", "2017", "5", "A" , "3.3", 110 );
    students[32] = new StudentEntity("C191002","Sayem", "2017", "5", "B" , "3.2", 110 );
    students[33] = new StudentEntity("C191003","Sadif", "2017", "5", "A" , "3.4", 115 );
    students[34] = new StudentEntity("C191004","Selim", "2017", "5", "A" , "3.6", 115 );
    students[35] = new StudentEntity("C191005","Sadek", "2017", "5", "B" , "3.4", 110 );
    students[36] = new StudentEntity("C191006","Shuvo", "2017", "5", "C" , "3.5", 115 );
    students[37] = new StudentEntity("C191007","Sumon", "2017", "5", "A" , "3.6", 110 );
    students[38] = new StudentEntity("C191008","Shipu", "2017", "5", "B" , "3.3", 110 );
    students[39] = new StudentEntity("C191009","Sayed", "2017", "5", "A" , "3.4", 110 );
    students[40] = new StudentEntity("C191010","Salek", "2017", "5", "C" , "3.5", 115 );

    students[41] = new StudentEntity("C201001","Adib", "2018", "4", "B" , "3.3", 100);
    students[42] = new StudentEntity("C201002","Akram", "2018", "4", "A" , "3.3", 105);
    students[43] = new StudentEntity("C201003","Alvi", "2018", "4", "C" , "3.6", 105);
    students[44] = new StudentEntity("C201004","Arefin", "2018", "4", "B" , "3.2", 100);
    students[45] = new StudentEntity("C201005","Arju", "2018", "4", "A" , "3.7", 105);
    students[46] = new StudentEntity("C201006","Amir", "2018", "4", "C" , "3.2", 100);
    students[47] = new StudentEntity("C201007","Akash", "2018", "4", "A" , "3.3", 100);
    students[48] = new StudentEntity("C201008","Amanullah", "2018", "4", "A" , "3.4", 105);
    students[49] = new StudentEntity("C201009","Aziz", "2018", "4", "B" , "3.5", 100);
    students[50] = new StudentEntity("C201010","Adib10", "2018", "4", "C" , "3.6", 105);

    students[51] = new StudentEntity("C211001","Mashrafi", "2018", "3", "B" , "3.3", 90);
    students[52] = new StudentEntity("C211002","Tamim", "2018", "3", "B" , "3.3", 95);
    students[53] = new StudentEntity("C211003","Sakib", "2018", "3", "B" , "3.6", 90);
    students[54] = new StudentEntity("C211004","Mushfiq", "2018", "3", "B" , "3.2", 95);
    students[55] = new StudentEntity("C211005","Mahmudullah", "2018", "3", "B" , "3.7", 90);
    students[56] = new StudentEntity("C211006","Mustafiz", "2018", "3", "B" , "3.2", 95);
    students[57] = new StudentEntity("C211007","Taijul", "2018", "3", "B" , "3.3", 95);
    students[58] = new StudentEntity("C211008","Taskin", "2018", "3", "B" , "3.4", 90);
    students[59] = new StudentEntity("C211009","Miraj", "2018", "3", "B" , "3.5", 90);
    students[60] = new StudentEntity("C211010","Liton", "2018", "3", "B" , "3.5", 95);

    students[61] = new StudentEntity("C221001","Neymar", "2019", "2", "B" , "3.2", 80);
    students[62] = new StudentEntity("C221002","Oskar", "2019", "2", "B" , "3.3", 85);
    students[63] = new StudentEntity("C221003","Coutinho", "2019", "2", "A" , "3.7", 85);
    students[64] = new StudentEntity("C221004","Silva", "2019", "2", "B" , "3.4", 85);
    students[65] = new StudentEntity("C221005","Casemiro", "2019", "2", "A" , "3.5", 85);
    students[66] = new StudentEntity("C221006","Fabinho", "2019", "2", "A" , "3.6", 80);
    students[67] = new StudentEntity("C221007","Allison", "2019", "2", "B" , "3.3", 80);
    students[68] = new StudentEntity("C221008","Firmino", "2019", "2", "C" , "3.5", 80);
    students[69] = new StudentEntity("C221009","Militao", "2019", "2", "B" , "3.4", 80);
    students[70] = new StudentEntity("C221010","Marcelo", "2019", "2", "C" , "3.7", 80);

    students[71] = new StudentEntity("C231001","Messi", "2019", "1", "B" , "3.6", 70);
    students[72] = new StudentEntity("C231002","Ronaldo", "2019", "1", "B" , "3.7", 70);
    students[73] = new StudentEntity("C231003","Pele", "2019", "1", "A" , "3.3", 70);
    students[74] = new StudentEntity("C231004","Meradona", "2019", "1", "B" , "3.6", 75);
    students[75] = new StudentEntity("C231005","Cryff", "2019", "1", "A" , "3.7", 70);
    students[76] = new StudentEntity("C231006","Backham", "2019", "1", "C" , "3.8", 70);
    students[77] = new StudentEntity("C231007","Ronaldinho", "2019", "1", "C" , "3.4", 75);
    students[78] = new StudentEntity("C231008","Carlos", "2019", "1", "B" , "3.2", 75);
    students[79] = new StudentEntity("C231009","Cafu", "2019", "1", "C" , "3.1", 70);
    students[80] = new StudentEntity("C231010","Zidan", "2019", "1", "A" , "3.9", 75);

    List<StudentEntity> studentEntities = List.of(students);
    studentRepository.saveAll(studentEntities);
  }
}
