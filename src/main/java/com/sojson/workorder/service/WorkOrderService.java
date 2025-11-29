package com.sojson.workorder.service;

import java.util.List;
import java.util.Map;

import com.sojson.core.mybatis.page.Pagination;
import com.sojson.workorder.model.WorkOrder;

public interface WorkOrderService {
    int deleteByPrimaryKey(Long id);

    WorkOrder insert(WorkOrder record);

    WorkOrder insertSelective(WorkOrder record);

    WorkOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkOrder record);

    int updateByPrimaryKey(WorkOrder record);
    
    Pagination<WorkOrder> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    
    List<WorkOrder> selectByCondition(Map<String, Object> condition);
    
    int countByCondition(Map<String, Object> condition);
}