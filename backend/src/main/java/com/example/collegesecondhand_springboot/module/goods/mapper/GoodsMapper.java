package com.example.collegesecondhand_springboot.module.goods.mapper;

    import com.example.collegesecondhand_springboot.module.goods.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    //sql->查询
    @Select("SELECT * FROM goods")
    List<Goods> findAll();

    //sql->根据Sid查询数据
    @Select("SELECT * FROM goods WHERE Sid=#{Sid} AND (IFNULL(Gstatus,'') LIKE concat('%', #{Gstatus}, '%')) AND (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')) ORDER BY Gtime DESC")
    List<Goods> getSidGoods(@Param("Sid") String Sid,@Param("Gstatus") String Gstatus,@Param("Gname") String Gname);

    //sql->根据id查询所有数据
    @Select("SELECT * FROM goods WHERE Gid=#{Gid}")
    Goods findOne(@Param("Gid") Integer Gid);

    //sql->插入(只能由学生用户去添加商品数据)
    @Insert("INSERT INTO goods(Sid,Gname,Gcategory,Gbuyprice,Gsellprice,Gphoto,Gdescribe,SELLusername) VALUES (#{Sid},#{Gname},#{Gcategory},#{Gbuyprice},#{Gsellprice},#{Gphoto},#{Gdescribe},#{SELLusername})")
    Boolean insertGoods(Goods goods);

    //sql->修改商品状态(学生用户修改商品状态)
    @Update("UPDATE goods SET Gstatus=#{Gstatus} WHERE Gid=#{Gid}")
    Boolean updateGoodsGstatus(@Param("Gid") String Gid,@Param("Gstatus") String Gstatus);

    //sql->修改(管理员修改商品信息)
    /*@Update("UPDATE goods SET Gname=#{Gname},Gbuyprice=#{Gbuyprice},Gsellprice=#{Gsellprice},Gphoto=#{Gphoto},Gdescribe=#{Gdescribe} WHERE Gid=#{Gid}")*/
    Boolean updateGoods(Goods goods);

    //sql->修改(学生用户修改商品信息)
    Boolean updateStudentGoods(Goods goods);

    //sql->删除商品前先删关联表数据
    @Delete("DELETE FROM `collection` WHERE Gid=#{Gid}")
    Integer deleteCollectionByGid(@Param("Gid") long Gid);

    @Delete("DELETE FROM `leave` WHERE Gid=#{Gid}")
    Integer deleteLeaveByGid(@Param("Gid") long Gid);

    @Delete("DELETE FROM `order` WHERE Gid=#{Gid}")
    Integer deleteOrderByGid(@Param("Gid") long Gid);

    //sql->删除
    @Delete("DELETE FROM goods WHERE Gid=#{Gid}")
    Integer deleteGoods(long Gid);

    //批量删除关联表数据
    @Delete("<script>DELETE FROM `collection` WHERE Gid IN <foreach item='id' collection='list' open='(' separator=',' close=')'>#{id}</foreach></script>")
    Integer deleteCollectionBatch(List<Integer> Gids);

    @Delete("<script>DELETE FROM `leave` WHERE Gid IN <foreach item='id' collection='list' open='(' separator=',' close=')'>#{id}</foreach></script>")
    Integer deleteLeaveBatch(List<Integer> Gids);

    @Delete("<script>DELETE FROM `order` WHERE Gid IN <foreach item='id' collection='list' open='(' separator=',' close=')'>#{id}</foreach></script>")
    Integer deleteOrderBatch(List<Integer> Gids);

    //mybatis的xml方式用sql->批量删除
    Integer deleteGoodsBatch(List<Integer> Gids);

    //sql->学生分页查询（支持分类筛选、关键词搜索、动态排序）
    @Select("<script>" +
        "SELECT * FROM goods WHERE Gaudit='1'" +
        "<if test='category != null and category != \"\"'>" +
        " AND Gcategory = #{category}" +
        "</if>" +
        "<if test='Gname != null and Gname != \"\"'>" +
        " AND (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')" +
        " OR IFNULL(Gdescribe,'') LIKE concat('%', #{Gname}, '%'))" +
        "</if>" +
        "<choose>" +
        "<when test='sortBy == \"price_asc\"'> ORDER BY CAST(IFNULL(Gsellprice,'0') AS DECIMAL(10,2)) ASC</when>" +
        "<when test='sortBy == \"price_desc\"'> ORDER BY CAST(IFNULL(Gsellprice,'0') AS DECIMAL(10,2)) DESC</when>" +
        "<otherwise> ORDER BY Gtime DESC</otherwise>" +
        "</choose>" +
        " limit #{pageNum}, #{pageSize}" +
        "</script>")
    List<Goods> getGoodsPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("Gname") String Gname, @Param("sortBy") String sortBy, @Param("category") String category);

    //sql->查询Sid所有数据量
    @Select("SELECT count(*) FROM goods WHERE Sid=#{Sid} AND (IFNULL(Gstatus,'') LIKE concat('%', #{Gstatus}, '%')) AND (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%'))")
    int getSidTotal(@Param("Sid") String Sid,@Param("Gstatus") String Gstatus,@Param("Gname") String Gname);

    //sql->学生查询所有数据量（支持分类筛选+关键词搜索）
    @Select("<script>" +
        "SELECT count(*) FROM goods WHERE Gaudit='1'" +
        "<if test='category != null and category != \"\"'>" +
        " AND Gcategory = #{category}" +
        "</if>" +
        "<if test='Gname != null and Gname != \"\"'>" +
        " AND (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')" +
        " OR IFNULL(Gdescribe,'') LIKE concat('%', #{Gname}, '%'))" +
        "</if>" +
        "</script>")
    int getTotal(@Param("Gname") String Gname, @Param("category") String category);

    //sql->查询商品总数
    @Select("SELECT COUNT(*) FROM goods")
    int getAllTotal();

    //sql->管理员分页查询
    @Select("SELECT *  FROM goods WHERE (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')) ORDER BY Gtime DESC limit #{pageNum}, #{pageSize} ")
    List<Goods> getAdminGoodsPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("Gname") String Gname);

    //sql->管理员查询所有数据量
    @Select("SELECT count(*) FROM goods WHERE (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%'))")
    int getAdminTotal(@Param("Gname") String Gname);
}
