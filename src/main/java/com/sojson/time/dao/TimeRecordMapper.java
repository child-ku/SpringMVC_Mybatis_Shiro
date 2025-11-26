package com.sojson.time.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sojson.time.model.TimeRecord;

public interface TimeRecordMapper {
    /**
     * Delete by primary key
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert record
     */
    int insert(TimeRecord record);

    /**
     * Insert record selectively
     */
    int insertSelective(TimeRecord record);

    /**
     * Select by primary key
     */
    TimeRecord selectByPrimaryKey(Long id);

    /**
     * Update record selectively by primary key
     */
    int updateByPrimaryKeySelective(TimeRecord record);

    /**
     * Update record by primary key
     */
    int updateByPrimaryKey(TimeRecord record);

    /**
     * Select time records by user id
     */
    List<TimeRecord> selectByUserId(Long userId);

    /**
     * Select time records by project id
     */
    List<TimeRecord> selectByProjectId(Long projectId);

    /**
     * Select time records by user id and project id
     */
    List<TimeRecord> selectByUserIdAndProjectId(@Param("userId") Long userId, @Param("projectId") Long projectId);

    /**
     * Select time records by user id and work date
     */
    List<TimeRecord> selectByUserIdAndWorkDate(@Param("userId") Long userId, @Param("workDate") String workDate);

    /**
     * Select time records by user id and date range
     */
    List<TimeRecord> selectByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * Select time records by project id and date range
     */
    List<TimeRecord> selectByProjectIdAndDateRange(@Param("projectId") Long projectId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * Select all time records
     */
    List<TimeRecord> selectAll();
}