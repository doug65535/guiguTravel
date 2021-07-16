package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.util.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    OrderSettingService orderSettingService;

    /**
     *                         var param = {
     *                             value:value,
     *                             orderDate:str
     *                         }
     * @param map
     * @return
     */
    @RequestMapping("/editNumberByOrderDate")
    public Result editNumberByOrderDate(@RequestBody Map map){
        try {
            orderSettingService.editNumberByOrderDate(map);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }


    /*this.leftobj = [
    { date: 1, number: 120, reservations: 1 },
    { date: 3, number: 120, reservations: 1 },
    { date: 4, number: 120, reservations: 120 },
    { date: 6, number: 120, reservations: 1 },
    { date: 8, number: 120, reservations: 1 }
    ];*/
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> list=orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_SUCCESS);
        }
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);

            List<OrderSetting> listData = new ArrayList<OrderSetting>();
            for (String[] strArray : list) {
                String dateStr = strArray[0];
                String numberStr = strArray[1];
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(dateStr));
                orderSetting.setNumber(Integer.parseInt(numberStr));
                //orderSetting.setReservations(0);
                listData.add(orderSetting);
            }

            orderSettingService.addBatch(listData);

            return new Result(true, MessageConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.UPLOAD_FAIL);
        }

    }

}
