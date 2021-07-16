package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void editNumberByOrderDate(Map map) {
        try {
            String dateSte = (String)map.get("orderDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateSte);
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.setNumber(Integer.parseInt((String)map.get("value")));
            orderSetting.setOrderDate(date);
            int count = orderSettingDao.findOrderSettingByOrderDate(date);
            if(count>0){
                orderSettingDao.edit(orderSetting);
            }else{

                orderSettingDao.add(orderSetting);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) { //2021-1
        String startDate = date+"-1"; //  2021-1-1
        String endDate = date+"-31"; //  2021-1-31
        Map param = new HashMap();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(param);

        List<Map> listData = new ArrayList<Map>();

        for (OrderSetting orderSetting : list) {
            Map map = new HashMap();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            listData.add(map);
        }

        return listData;
    }

    @Override
    public void addBatch(List<OrderSetting> listData) {
        for (OrderSetting orderSetting : listData) {
            //如果日期对应设置存在，就修改，否则就添加
            int count = orderSettingDao.findOrderSettingByOrderDate(orderSetting.getOrderDate());
            if(count>0){ //预约设置存在了
                orderSettingDao.edit(orderSetting);
            }else{
                orderSettingDao.add(orderSetting);
            }
        }
    }
}
