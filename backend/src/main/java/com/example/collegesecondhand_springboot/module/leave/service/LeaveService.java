package com.example.collegesecondhand_springboot.module.leave.service;

    import com.example.collegesecondhand_springboot.module.leave.controller.LeaveController;
    import com.example.collegesecondhand_springboot.module.goods.entity.Goods;
    import com.example.collegesecondhand_springboot.module.leave.entity.Leave;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface LeaveService {
    Boolean insertLeave(@Param("Sid") String Sid, @Param("Gid") String Gid,@Param("Lmessage") String Lmessage);
    Integer deleteLeave(long Lid,long Sid);
    List<Leave> getLeavePage(Integer pageNum, Integer pageSize, String Gid);
    int getGidTotal(String Gid);
    List<Leave> getLeavePageSid(Integer pageNum, Integer pageSize, String Sid);
    int getSidTotal(String Sid);
}
