package com.example.collegesecondhand_springboot.module.collection.service;

    import com.example.collegesecondhand_springboot.module.admin.entity.Admin;
    import com.example.collegesecondhand_springboot.module.collection.entity.Collection;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface CollectionService {
    List<Collection> findCollection(String Sid,String Gname);
    int getSidTotal(String Sid,String Gname);
    Boolean findMyCollection(@Param("Sid") String Sid,@Param("Gid") String Gid);
    Boolean insertCollection(@Param("Sid") String Sid,@Param("Gid") String Gid);
    Integer deleteCollection(long Sid,long Gid);
}