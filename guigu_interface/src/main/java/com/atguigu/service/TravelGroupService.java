package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

public interface TravelGroupService {
    void add(Integer[] travelItemIds, TravelGroup travelGroup);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    TravelGroup getById(Integer id);

    List<Integer> getTravelitemIdsByTravelGroupId(Integer travelGroupId);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    List<TravelGroup> findAll();
}
