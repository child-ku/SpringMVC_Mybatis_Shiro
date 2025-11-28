package com.sojson.conference.dao;

import com.sojson.conference.model.ConferenceRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Conference Room Mapper Interface
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public interface ConferenceRoomMapper {
    /**
     * Delete conference room by primary key
     * @param id Conference room ID
     * @return Number of affected rows
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert conference room record
     * @param record Conference room object
     * @return Number of affected rows
     */
    int insert(ConferenceRoom record);

    /**
     * Insert selective conference room record
     * @param record Conference room object
     * @return Number of affected rows
     */
    int insertSelective(ConferenceRoom record);

    /**
     * Select conference room by primary key
     * @param id Conference room ID
     * @return Conference room object
     */
    ConferenceRoom selectByPrimaryKey(Long id);

    /**
     * Update conference room record by primary key selective
     * @param record Conference room object
     * @return Number of affected rows
     */
    int updateByPrimaryKeySelective(ConferenceRoom record);

    /**
     * Update conference room record by primary key
     * @param record Conference room object
     * @return Number of affected rows
     */
    int updateByPrimaryKey(ConferenceRoom record);

    /**
     * Find all conference rooms
     * @param map Query conditions
     * @return List of conference rooms
     */
    List<ConferenceRoom> findAll(Map<String, Object> map);

    /**
     * Count all conference rooms
     * @param map Query conditions
     * @return Number of conference rooms
     */
    int findCount(Map<String, Object> map);

    /**
     * Find available conference rooms by time range
     * @param startTime Start time
     * @param endTime End time
     * @return List of available conference rooms
     */
    List<ConferenceRoom> findAvailableRooms(@Param("startTime") String startTime, @Param("endTime") String endTime);
}