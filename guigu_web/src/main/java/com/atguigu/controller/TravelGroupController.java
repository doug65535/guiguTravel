package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference
    TravelGroupService travelGroupService;

    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> listAll = travelGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,listAll);
    }


    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){ //接收2部分数据
        try {
            travelGroupService.edit(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/getTravelitemIdsByTravelGroupId")
    public Result getTravelitemIdsByTravelGroupId(Integer travelGroupId){
        try {
            List<Integer> travelitemIds= travelGroupService.getTravelitemIdsByTravelGroupId(travelGroupId);
            return new Result(true,"根据跟团游查询自由行成功",travelitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"根据跟团游查询自由行失败");
        }
    }

    @RequestMapping("/getById")
    public Result getById(Integer id){
        try {
            TravelGroup travelGroup = travelGroupService.getById(id);
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup); //回显表单数据
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelGroupService.findPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){ //接收2部分数据
        try {
            travelGroupService.add(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }






}
