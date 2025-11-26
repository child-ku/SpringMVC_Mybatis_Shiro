package com.sojson.time.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.time.dao.TimeRecordMapper;
import com.sojson.time.dao.TimeModificationRequestMapper;
import com.sojson.time.model.TimeRecord;
import com.sojson.time.model.TimeModificationRequest;
import com.sojson.time.service.TimeService;

@Service
public class TimeServiceImpl extends BaseMybatisDao<TimeRecordMapper> implements TimeService {
    
    @Autowired
    private TimeRecordMapper timeRecordMapper;
    
    @Autowired
    private TimeModificationRequestMapper timeModificationRequestMapper;

    // Time Record Management
    @Override
    public int deleteTimeRecordById(Long id) {
        return timeRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertTimeRecord(TimeRecord record) {
        return timeRecordMapper.insert(record);
    }

    @Override
    public int insertTimeRecordSelective(TimeRecord record) {
        return timeRecordMapper.insertSelective(record);
    }

    @Override
    public TimeRecord getTimeRecordById(Long id) {
        return timeRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateTimeRecordByIdSelective(TimeRecord record) {
        return timeRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateTimeRecordById(TimeRecord record) {
        return timeRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TimeRecord> getTimeRecordsByUserId(Long userId) {
        return timeRecordMapper.selectByUserId(userId);
    }

    @Override
    public List<TimeRecord> getTimeRecordsByProjectId(Long projectId) {
        return timeRecordMapper.selectByProjectId(projectId);
    }

    @Override
    public List<TimeRecord> getTimeRecordsByUserIdAndProjectId(Long userId, Long projectId) {
        return timeRecordMapper.selectByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<TimeRecord> getTimeRecordsByUserIdAndWorkDate(Long userId, String workDate) {
        return timeRecordMapper.selectByUserIdAndWorkDate(userId, workDate);
    }

    @Override
    public List<TimeRecord> getTimeRecordsByUserIdAndDateRange(Long userId, String startDate, String endDate) {
        return timeRecordMapper.selectByUserIdAndDateRange(userId, startDate, endDate);
    }

    @Override
    public List<TimeRecord> getTimeRecordsByProjectIdAndDateRange(Long projectId, String startDate, String endDate) {
        return timeRecordMapper.selectByProjectIdAndDateRange(projectId, startDate, endDate);
    }

    @Override
    public List<TimeRecord> getAllTimeRecords() {
        return timeRecordMapper.selectAll();
    }

    // Time Modification Request Management
    @Override
    public int deleteTimeModificationRequestById(Long id) {
        return timeModificationRequestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertTimeModificationRequest(TimeModificationRequest record) {
        return timeModificationRequestMapper.insert(record);
    }

    @Override
    public int insertTimeModificationRequestSelective(TimeModificationRequest record) {
        return timeModificationRequestMapper.insertSelective(record);
    }

    @Override
    public TimeModificationRequest getTimeModificationRequestById(Long id) {
        return timeModificationRequestMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateTimeModificationRequestByIdSelective(TimeModificationRequest record) {
        return timeModificationRequestMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateTimeModificationRequestById(TimeModificationRequest record) {
        return timeModificationRequestMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TimeModificationRequest> getTimeModificationRequestsByTimeRecordId(Long timeRecordId) {
        return timeModificationRequestMapper.selectByTimeRecordId(timeRecordId);
    }

    @Override
    public List<TimeModificationRequest> getTimeModificationRequestsByUserId(Long userId) {
        return timeModificationRequestMapper.selectByUserId(userId);
    }

    @Override
    public List<TimeModificationRequest> getTimeModificationRequestsByStatus(String status) {
        return timeModificationRequestMapper.selectByStatus(status);
    }

    @Override
    public List<TimeModificationRequest> getTimeModificationRequestsByUserIdAndStatus(Long userId, String status) {
        return timeModificationRequestMapper.selectByUserIdAndStatus(userId, status);
    }

    @Override
    public List<TimeModificationRequest> getTimeModificationRequestsByProjectIdAndStatus(Long projectId, String status) {
        return timeModificationRequestMapper.selectByProjectIdAndStatus(projectId, status);
    }

    @Override
    public List<TimeModificationRequest> getAllTimeModificationRequests() {
        return timeModificationRequestMapper.selectAll();
    }

    // Business Methods
    @Override
    public int submitTimeModificationRequest(TimeModificationRequest request) {
        // Set initial status to "PENDING"
        request.setStatus("PENDING");
        return timeModificationRequestMapper.insertSelective(request);
    }

    @Override
    public int approveTimeModificationRequest(Long requestId, Long reviewerId, String reviewComment) {
        TimeModificationRequest request = getTimeModificationRequestById(requestId);
        if (request != null) {
            // Update request status to "APPROVED"
            request.setStatus("APPROVED");
            request.setReviewerId(reviewerId);
            request.setReviewComment(reviewComment);
            
            // Update the corresponding time record
            TimeRecord timeRecord = getTimeRecordById(request.getTimeRecordId());
            if (timeRecord != null) {
                timeRecord.setHours(request.getNewHours());
                timeRecord.setDescription(request.getNewDescription());
                updateTimeRecordByIdSelective(timeRecord);
            }
            
            return updateTimeModificationRequestByIdSelective(request);
        }
        return 0;
    }

    @Override
    public int rejectTimeModificationRequest(Long requestId, Long reviewerId, String reviewComment) {
        TimeModificationRequest request = getTimeModificationRequestById(requestId);
        if (request != null) {
            // Update request status to "REJECTED"
            request.setStatus("REJECTED");
            request.setReviewerId(reviewerId);
            request.setReviewComment(reviewComment);
            
            return updateTimeModificationRequestByIdSelective(request);
        }
        return 0;
    }
}