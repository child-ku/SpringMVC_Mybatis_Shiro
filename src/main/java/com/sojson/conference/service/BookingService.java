package com.sojson.conference.service;

import com.sojson.conference.model.Booking;
import com.sojson.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Booking Service Interface
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public interface BookingService {
    /**
     * Delete booking by primary key
     * @param id Booking ID
     * @return Number of affected rows
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert booking record
     * @param record Booking object
     * @return Booking object
     */
    Booking insert(Booking record);

    /**
     * Insert selective booking record
     * @param record Booking object
     * @return Booking object
     */
    Booking insertSelective(Booking record);

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
     * @param pageNo Page number
     * @param pageSize Page size
     * @return Pagination result
     */
    Pagination<Booking> findByPage(Map<String, Object> map, Integer pageNo, Integer pageSize);

    /**
     * Find all bookings without pagination
     * @param map Query conditions
     * @return List of bookings
     */
    List<Booking> findAll(Map<String, Object> map);

    /**
     * Find bookings by user ID
     * @param userId User ID
     * @return List of bookings
     */
    List<Booking> findByUserId(Long userId);

    /**
     * Check booking conflict
     * @param roomId Room ID
     * @param startTime Start time
     * @param endTime End time
     * @param bookingId Booking ID (optional, for update)
     * @return Number of conflicting bookings
     */
    int checkConflict(Long roomId, String startTime, String endTime, Long bookingId);
}