package com.sojson.workorder.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.workorder.dao.WorkOrderCommentMapper;
import com.sojson.workorder.model.WorkOrderComment;
import com.sojson.workorder.service.WorkOrderCommentService;

@Service
public class WorkOrderCommentServiceImpl implements WorkOrderCommentService {

    @Autowired
    WorkOrderCommentMapper workOrderCommentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return workOrderCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WorkOrderComment insert(WorkOrderComment record) {
        workOrderCommentMapper.insert(record);
        return record;
    }

    @Override
    public WorkOrderComment insertSelective(WorkOrderComment record) {
        workOrderCommentMapper.insertSelective(record);
        return record;
    }

    @Override
    public WorkOrderComment selectByPrimaryKey(Long id) {
        return workOrderCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkOrderComment record) {
        return workOrderCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkOrderComment record) {
        return workOrderCommentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkOrderComment> selectByWorkOrderId(Long workOrderId) {
        return workOrderCommentMapper.selectByWorkOrderId(workOrderId);
    }

    @Override
    public List<WorkOrderComment> selectByCondition(Map<String, Object> condition) {
        return workOrderCommentMapper.selectByCondition(condition);
    }
}