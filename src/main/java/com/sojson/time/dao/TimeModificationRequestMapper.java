package com.sojson.time.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sojson.time.model.TimeModificationRequest;

public interface TimeModificationRequestMapper {
    /**
     * Delete by primary key
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert record
     */
    int insert(TimeModificationRequest record);

    /**
     * Insert record selectively
     */
    int insertSelective(TimeModificationRequest record);

    /**
     * Select by primary key
     */
    TimeModificationRequest selectByPrimaryKey(Long id);

    /**
     * Update record selectively by primary key
     */
    int updateByPrimaryKeySelective(TimeModificationRequest record);

    /**
     * Update record by primary key
     */
    int updateByPrimaryKey(TimeModificationRequest record);

    /**
     * Select time modification requests by time record id
     */
    List<TimeModificationRequest> selectByTimeRecordId(Long timeRecordId);

    /**
     * Select time modification requests by user id
     */
    List<TimeModificationRequest> selectByUserId(Long userId);

    /**
     * Select time modification requests by status
     */
    List<TimeModificationRequest> selectByStatus(String status);

    /**
     * Select time modification requests by user id and status
     */
    List<TimeModificationRequest> selectByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);

    /**
     * Select time modification requests by project id and status
     */
    List<TimeModificationRequest> selectByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") String status);

    /**
     * Select all time modification requests
     */
    List<TimeModificationRequest> selectAll();
}