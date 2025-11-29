package com.sojson.workorder.dao;

import java.util.List;
import java.util.Map;

import com.sojson.workorder.model.WorkOrderComment;

public interface WorkOrderCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkOrderComment record);

    int insertSelective(WorkOrderComment record);

    WorkOrderComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkOrderComment record);

    int updateByPrimaryKey(WorkOrderComment record);
    
    List<WorkOrderComment> selectByWorkOrderId(Long workOrderId);
    
    List<WorkOrderComment> selectByCondition(Map<String, Object> condition);
}