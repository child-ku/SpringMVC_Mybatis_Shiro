package com.sojson.conference.service;

import com.sojson.conference.model.ConferenceRoom;
import com.sojson.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Conference Room Service Interface
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public interface ConferenceRoomService {
    /**
     * Delete conference room by primary key
     * @param id Conference room ID
     * @return Number of affected rows
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert conference room record
     * @param record Conference room object
     * @return Conference room object
     */
    ConferenceRoom insert(ConferenceRoom record);

    /**
     * Insert selective conference room record
     * @param record Conference room object
     * @return Conference room object
     */
    ConferenceRoom insertSelective(ConferenceRoom record);

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
     * @param pageNo Page number
     * @param pageSize Page size
     * @return Pagination result
     */
    Pagination<ConferenceRoom> findByPage(Map<String, Object> map, Integer pageNo, Integer pageSize);

    /**
     * Find all conference rooms without pagination
     * @param map Query conditions
     * @return List of conference rooms
     */
    List<ConferenceRoom> findAll(Map<String, Object> map);

    /**
     * Find available conference rooms by time range
     * @param startTime Start time
     * @param endTime End time
     * @return List of available conference rooms
     */
    List<ConferenceRoom> findAvailableRooms(String startTime, String endTime);
}