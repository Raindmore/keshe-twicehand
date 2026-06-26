package com.example.collegesecondhand_springboot.module.goods.controller;

    import com.example.collegesecondhand_springboot.common.constants.Constants;
    import com.example.collegesecondhand_springboot.common.result.Result;
    import com.example.collegesecondhand_springboot.module.goods.entity.Goods;
    import com.example.collegesecondhand_springboot.common.exception.ServiceException;
    import com.example.collegesecondhand_springboot.module.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //查询
    @GetMapping("/getGoods")
    public List<Goods> getGoods() {
        return goodsService.findAll();
    }

    //根据sid查询数据
    @GetMapping("/getSidGoods")
    public Map<String, Object> getSidGoods(@RequestParam("Sid") String Sid,@RequestParam("Gstatus") String Gstatus,@RequestParam("Gname") String Gname) {

        List<Goods> data = goodsService.getSidGoods(Sid,Gstatus,Gname);
        Integer total = goodsService.getSidTotal(Sid,Gstatus,Gname);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //根据id查询数据
    @GetMapping("/getOneGoods")
    public Goods findOne(@RequestParam("Gid") Integer Gid){
        return goodsService.findOne(Gid);
    }

    //增加
    @PostMapping("/insertGoods")
    public Result addGoods(@RequestBody Goods goods) {
        if(goodsService.insertGoods(goods))
        {
            return Result.success("添加成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }

    }

    //修改商品状态
    @PutMapping("/updateGoodsGstatus")
    public Result updateGoodsGstatus(@RequestParam("Gid") String Gid,@RequestParam("Gstatus") String Gstatus){
        if(goodsService.updateGoodsGstatus(Gid,Gstatus))
        {
            return Result.success("交易成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }
    }


    //管理员修改
    @PutMapping("/updateGoods")
    public Result updateGoods(@RequestBody Goods goods){
        if(goods.getGaudit().equals("0"))
        {
            goods.setGstatus("0");
        }
        else if(goods.getGaudit().equals("1"))
        {
            goods.setGstatus("1");
        }
        if(goodsService.updateGoods(goods))
        {
            return Result.success("修改成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }
    }
    //学生用户修改
    @PutMapping("/updateStudentGoods")
    public Result updateStudentGoods(@RequestBody Goods goods){
        if(goodsService.updateStudentGoods(goods))
        {
            return Result.success("修改成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }
    }

    //删除
    @DeleteMapping("/deleteGoods/{Gid}")
    public Integer deleteGoods(@PathVariable("Gid") Long Gid){
        return goodsService.deleteGoods(Gid);
    }
    //批量删除
    @PostMapping("/deleteGoods/batch")
    public Integer deleteGoodsBatch(@RequestBody List<Integer> Gids){
        return goodsService.deleteGoodsBatch(Gids);
    }

    //学生分页查询
    // 接口路径：/goods/getGoodsPage?pageNum=1&pageSize=10&Gname=&sortBy=default&category=
    // sortBy: default | price_asc | price_desc | newest
    // category: 分类（教材/电子/生活/服饰/运动/其他），通过Gcategory精确匹配
    // Gname: 关键词搜索，搜索商品名称和描述
    @GetMapping("/getGoodsPage")
    public Map<String, Object> getGoodsPage(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam(required = false, defaultValue = "") String Gname,
                                           @RequestParam(required = false, defaultValue = "default") String sortBy,
                                           @RequestParam(required = false, defaultValue = "") String category) {
        pageNum = (pageNum - 1) * pageSize;
        // newest 等同于 default (按时间降序)
        String orderBy = "newest".equals(sortBy) ? "default" : sortBy;
        List<Goods> data = goodsService.getGoodsPage(pageNum, pageSize, Gname, orderBy, category);
        Integer total = goodsService.getTotal(Gname, category);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //管理员分页查询
    // 接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    // limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/getAdminGoodsPage")
    public Map<String, Object> getAdminGoodsPage(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize,
                                            @RequestParam String Gname) {
        pageNum = (pageNum - 1) * pageSize;
        List<Goods> data = goodsService.getAdminGoodsPage(pageNum, pageSize, Gname);
        Integer total = goodsService.getAdminTotal(Gname);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //查询总商品数
    @GetMapping("/getGoodsAllTotal")
    public int getAllTotal(){
        return goodsService.getAllTotal();
    }
}
