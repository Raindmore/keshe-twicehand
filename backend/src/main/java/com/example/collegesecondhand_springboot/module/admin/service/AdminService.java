package com.example.collegesecondhand_springboot.module.admin.service;

    import com.example.collegesecondhand_springboot.module.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface AdminService {
    Admin login(String Ausername,String Apassword);
    Boolean registerAdmin(@Param("Ausername") String Ausername,@Param("Apassword") String Apassword);
    List<Admin> findAll();
    String findId(@Param("Ausername") String Ausername);
    Admin findOne(@Param("Aid") Integer Aid);
    Boolean insertAdmin(Admin admin);
    Boolean updateAdmin(Admin admin);
    Boolean updatePassword(Integer Aid,String Apassword,String Anewpassword);
    Integer deleteAdmin(long Aid);
    Integer deleteAdminBatch(List<Integer> Aids);
    List<Admin> getAdminPage(Integer pageNum,Integer pageSize,String Ausername);
    int getTotal(String Ausername);
    int getAllTotal();
}