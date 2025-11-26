package com.sojson.time.service;

import java.util.List;
import com.sojson.time.model.TimeRecord;
import com.sojson.time.model.TimeModificationRequest;

public interface TimeService {
    // Time Record Management
    int deleteTimeRecordById(Long id);
    int insertTimeRecord(TimeRecord record);
    int insertTimeRecordSelective(TimeRecord record);
    TimeRecord getTimeRecordById(Long id);
    int updateTimeRecordByIdSelective(TimeRecord record);
    int updateTimeRecordById(TimeRecord record);
    List<TimeRecord> getTimeRecordsByUserId(Long userId);
    List<TimeRecord> getTimeRecordsByProjectId(Long projectId);
    List<TimeRecord> getTimeRecordsByUserIdAndProjectId(Long userId, Long projectId);
    List<TimeRecord> getTimeRecordsByUserIdAndWorkDate(Long userId, String workDate);
    List<TimeRecord> getTimeRecordsByUserIdAndDateRange(Long userId, String startDate, String endDate);
    List<TimeRecord> getTimeRecordsByProjectIdAndDateRange(Long projectId, String startDate, String endDate);
    List<TimeRecord> getAllTimeRecords();
    
    // Time Modification Request Management
    int deleteTimeModificationRequestById(Long id);
    int insertTimeModificationRequest(TimeModificationRequest record);
    int insertTimeModificationRequestSelective(TimeModificationRequest record);
    TimeModificationRequest getTimeModificationRequestById(Long id);
    int updateTimeModificationRequestByIdSelective(TimeModificationRequest record);
    int updateTimeModificationRequestById(TimeModificationRequest record);
    List<TimeModificationRequest> getTimeModificationRequestsByTimeRecordId(Long timeRecordId);
    List<TimeModificationRequest> getTimeModificationRequestsByUserId(Long userId);
    List<TimeModificationRequest> getTimeModificationRequestsByStatus(String status);
    List<TimeModificationRequest> getTimeModificationRequestsByUserIdAndStatus(Long userId, String status);
    List<TimeModificationRequest> getTimeModificationRequestsByProjectIdAndStatus(Long projectId, String status);
    List<TimeModificationRequest> getAllTimeModificationRequests();
    
    // Business Methods
    int submitTimeModificationRequest(TimeModificationRequest request);
    int approveTimeModificationRequest(Long requestId, Long reviewerId, String reviewComment);
    int rejectTimeModificationRequest(Long requestId, Long reviewerId, String reviewComment);
}