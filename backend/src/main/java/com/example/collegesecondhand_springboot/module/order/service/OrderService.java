package com.example.collegesecondhand_springboot.module.order.service;

    import com.example.collegesecondhand_springboot.module.goods.entity.Goods;
    import com.example.collegesecondhand_springboot.module.order.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order getOneOrder(String Oid);
    Order findOne(@Param("Oid") String Oid);
    List<Order> findOneBUY(String BUYSid,String Oid);
    int getBUYTotal(String BUYSid,String Oid);
    List<Order> findOneSELL(String SELLSid,String Oid);
    int getSELLTotal(String SELLSid,String Oid);
    Boolean insertOrder(String BUYSid,String SELLSid,String Gid,String Oprice);
    Integer deleteOrder(long Oid);
    Integer deleteOrderBatch(List<Integer> Oids);
    List<Order> getOrderPage(Integer pageNum, Integer pageSize, String Oid);
    int getTotal(String Oid);
    int getAllTotal();
    String getOpriceTotal();
}