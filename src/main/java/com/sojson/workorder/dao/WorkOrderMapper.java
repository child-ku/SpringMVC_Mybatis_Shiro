package com.sojson.workorder.dao;

import java.util.List;
import java.util.Map;

import com.sojson.workorder.model.WorkOrder;

public interface WorkOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkOrder record);

    int insertSelective(WorkOrder record);

    WorkOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkOrder record);

    int updateByPrimaryKey(WorkOrder record);
    
    List<WorkOrder> selectByCondition(Map<String, Object> condition);
    
    int countByCondition(Map<String, Object> condition);
}