package com.sojson.conference.dao;

import com.sojson.conference.model.Booking;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Booking Mapper Interface
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public interface BookingMapper {
    /**
     * Delete booking by primary key
     * @param id Booking ID
     * @return Number of affected rows
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert booking record
     * @param record Booking object
     * @return Number of affected rows
     */
    int insert(Booking record);

    /**
     * Insert selective booking record
     * @param record Booking object
     * @return Number of affected rows
     */
    int insertSelective(Booking record);

    /**
     * Select booking by primary key
     * @param id Booking ID
     * @return Booking object
     */
    Booking selectByPrimaryKey(Long id);

    /**
     * Update booking record by primary key selective
     * @param record Booking object
     * @return Number of affected rows
     */
    int updateByPrimaryKeySelective(Booking record);

    /**
     * Update booking record by primary key
     * @param record Booking object
     * @return Number of affected rows
     */
    int updateByPrimaryKey(Booking record);

    /**
     * Find all bookings
     * @param map Query conditions
     * @return List of bookings
     */
    List<Booking> findAll(Map<String, Object> map);

    /**
     * Count all bookings
     * @param map Query conditions
     * @return Number of bookings
     */
    int findCount(Map<String, Object> map);

    /**
     * Find bookings by user ID
     * @param userId User ID
     * @return List of bookings
     */
    List<Booking> findByUserId(Long userId);

    /**
     * Check booking conflict
     * @param roomId Conference room ID
     * @param startTime Start time
     * @param endTime End time
     * @return Number of conflicting bookings
     */
    int checkConflict(@Param("roomId") Long roomId, @Param("startTime") String startTime, @Param("endTime") String endTime);
}