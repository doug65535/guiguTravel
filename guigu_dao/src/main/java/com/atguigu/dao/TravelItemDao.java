package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    void delete(Integer id);

    TravelItem getById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    long findCountByTravelitemId(Integer id);

    /**
     * 帮助封装跟团游的travelItems属性方法
     * @param id 更团游的id
     * @return 这个跟团游对应的多个自由行数据
     */
    List<TravelItem> findTravelItemById(Integer id);
}
