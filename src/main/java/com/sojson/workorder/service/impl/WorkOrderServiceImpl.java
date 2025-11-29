package com.sojson.workorder.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.workorder.dao.WorkOrderMapper;
import com.sojson.workorder.model.WorkOrder;
import com.sojson.workorder.service.WorkOrderService;

@Service
public class WorkOrderServiceImpl extends BaseMybatisDao<WorkOrderMapper> implements WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return workOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WorkOrder insert(WorkOrder record) {
        workOrderMapper.insert(record);
        return record;
    }

    @Override
    public WorkOrder insertSelective(WorkOrder record) {
        workOrderMapper.insertSelective(record);
        return record;
    }

    @Override
    public WorkOrder selectByPrimaryKey(Long id) {
        return workOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkOrder record) {
        return workOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkOrder record) {
        return workOrderMapper.updateByPrimaryKey(record);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<WorkOrder> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public List<WorkOrder> selectByCondition(Map<String, Object> condition) {
        return workOrderMapper.selectByCondition(condition);
    }

    @Override
    public int countByCondition(Map<String, Object> condition) {
        return workOrderMapper.countByCondition(condition);
    }
}