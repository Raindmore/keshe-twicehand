package com.example.collegesecondhand_springboot.module.student.service;

    import com.example.collegesecondhand_springboot.module.admin.entity.Admin;
    import com.example.collegesecondhand_springboot.module.student.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface StudentService {
    Student login(String Susername, String Spassword);
    Boolean registerStudent(@Param("Susername") String Susername, @Param("Spassword") String Spassword);
    List<Student> findAll();
    String findId(@Param("Susername") String Susername);
    Student findOne(@Param("Sid") Integer Sid);
    Boolean insertStudent(Student student);
    Boolean updateStudent(Student student);
    Boolean updatePassword(Integer Sid,String Spassword,String Snewpassword);
    Integer deleteStudent(long Sid);
    Integer deleteStudentBatch(List<Integer> Sids);
    List<Student> getStudentPage(Integer pageNum,Integer pageSize,String Susername);
    int getTotal(String Susername);
    int getAllTotal();
}