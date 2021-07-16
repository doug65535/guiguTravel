package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    void addSetmealAndTravelGrop(Map<String, Integer> map);

    void add(Setmeal setmeal);

    Page findPage(String queryString);

    List getSetmeal();

    Setmeal findById(Integer id);

    Setmeal getSetmealById(Integer id);

    List<Map> getSetmealReport();
}
