package com.example.collegesecondhand_springboot.module.goods.service.impl;

import com.example.collegesecondhand_springboot.module.admin.entity.Admin;
import com.example.collegesecondhand_springboot.module.goods.entity.Goods;
import com.example.collegesecondhand_springboot.module.admin.mapper.AdminMapper;
import com.example.collegesecondhand_springboot.module.goods.mapper.GoodsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.collegesecondhand_springboot.module.goods.service.GoodsService;

@Service

public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


    //查询
    public List<Goods> findAll(){
        return goodsMapper.findAll();
    }

    //根据sid查询数据
    public List<Goods> getSidGoods(String Sid,String Gstatus,String Gname){
        return goodsMapper.getSidGoods(Sid,Gstatus,Gname);
    }

    //根据id查找所有数据
    public Goods findOne(@Param("Gid") Integer Gid){
        return goodsMapper.findOne(Gid);
    }

    //插入
    public Boolean insertGoods(Goods goods){
        try {
            return goodsMapper.insertGoods(goods);
        }catch (Exception e)
        {
            return false;
        }
    }

    //修改商品状态
    public Boolean updateGoodsGstatus(String Gid,String Gstatus){
        try {
            return goodsMapper.updateGoodsGstatus(Gid,Gstatus);
        }catch (Exception e)
        {
            return false;

        }

    }

    //管理员修改
    public Boolean updateGoods(Goods goods){
        try {
            return goodsMapper.updateGoods(goods);
        }catch (Exception e)
        {
            return false;

        }

    }

    //学生用户修改
    public Boolean updateStudentGoods(Goods goods){
        try {
            return goodsMapper.updateStudentGoods(goods);
        }catch (Exception e)
        {
            return false;

        }

    }

    //删除（先删关联表数据，再删商品）
    public Integer deleteGoods(long Gid){
        goodsMapper.deleteCollectionByGid(Gid);
        goodsMapper.deleteLeaveByGid(Gid);
        goodsMapper.deleteOrderByGid(Gid);
        return goodsMapper.deleteGoods(Gid);
    }

    //批量删除（先删关联表数据，再删商品）
    public Integer deleteGoodsBatch(List<Integer> Gids){
        goodsMapper.deleteCollectionBatch(Gids);
        goodsMapper.deleteLeaveBatch(Gids);
        goodsMapper.deleteOrderBatch(Gids);
        return goodsMapper.deleteGoodsBatch(Gids);
    }

    //学生分页查询（支持分类筛选、关键词搜索、动态排序）
    public List<Goods> getGoodsPage(Integer pageNum,Integer pageSize,String Gname,String sortBy,String category){
        return goodsMapper.getGoodsPage(pageNum, pageSize, Gname, sortBy, category);
    }

    //查询Sid的所有数据量
    public int getSidTotal(String Sid,String Gstatus,String Gname){
        return goodsMapper.getSidTotal(Sid,Gstatus,Gname);
    }

    //查询所有数据量（支持分类筛选+关键词搜索）
    public int getTotal(String Gname,String category){
        return goodsMapper.getTotal(Gname, category);
    }

    //查询商品总数
    public int getAllTotal(){
        return goodsMapper.getAllTotal();
    }

    //管理员分页查询
    public List<Goods> getAdminGoodsPage(Integer pageNum,Integer pageSize,String Gname){
        return goodsMapper.getAdminGoodsPage(pageNum, pageSize, Gname);
    }

    //管理员查询所有数据量
    public int getAdminTotal(String Gname){
        return goodsMapper.getAdminTotal(Gname);
    }
}

