package com.sojson.workorder.service;

import java.util.List;
import java.util.Map;

import com.sojson.workorder.model.WorkOrderComment;

public interface WorkOrderCommentService {
    int deleteByPrimaryKey(Long id);

    WorkOrderComment insert(WorkOrderComment record);

    WorkOrderComment insertSelective(WorkOrderComment record);

    WorkOrderComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkOrderComment record);

    int updateByPrimaryKey(WorkOrderComment record);
    
    List<WorkOrderComment> selectByWorkOrderId(Long workOrderId);
    
    List<WorkOrderComment> selectByCondition(Map<String, Object> condition);
}