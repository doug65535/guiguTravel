package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

public interface OrderService {
    Result saveOrder(Map map) throws Exception;

    Map<String, Object> findById(Integer orderId);
}
