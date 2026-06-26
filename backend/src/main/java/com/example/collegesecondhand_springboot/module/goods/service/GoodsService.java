package com.example.collegesecondhand_springboot.module.goods.service;

    import com.example.collegesecondhand_springboot.module.admin.entity.Admin;
    import com.example.collegesecondhand_springboot.module.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface GoodsService {
    List<Goods> findAll();
    List<Goods> getSidGoods(String Sid,String Gstatus,String Gname);
    Goods findOne(@Param("Gid") Integer Gid);
    Boolean insertGoods(Goods goods);
    Boolean updateGoodsGstatus(String Gid,String Gstatus);
    Boolean updateGoods(Goods goods);
    Boolean updateStudentGoods(Goods goods);
    Integer deleteGoods(long Gid);
    Integer deleteGoodsBatch(List<Integer> Gids);
    List<Goods> getGoodsPage(Integer pageNum,Integer pageSize,String Gname,String sortBy,String category);
    int getSidTotal(String Sid,String Gstatus,String Gname);
    int getTotal(String Gname,String category);
    int getAllTotal();
    List<Goods> getAdminGoodsPage(Integer pageNum,Integer pageSize,String Gname);
    int getAdminTotal(String Gname);
}