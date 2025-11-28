package com.sojson.conference.dao;

import com.sojson.conference.model.BookingApproval;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Booking Approval Mapper Interface
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public interface BookingApprovalMapper {
    /**
     * Delete booking approval by primary key
     * @param id Booking approval ID
     * @return Number of affected rows
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert booking approval record
     * @param record Booking approval object
     * @return Number of affected rows
     */
    int insert(BookingApproval record);

    /**
     * Insert selective booking approval record
     * @param record Booking approval object
     * @return Number of affected rows
     */
    int insertSelective(BookingApproval record);

    /**
     * Select booking approval by primary key
     * @param id Booking approval ID
     * @return Booking approval object
     */
    BookingApproval selectByPrimaryKey(Long id);

    /**
     * Update booking approval record by primary key selective
     * @param record Booking approval object
     * @return Number of affected rows
     */
    int updateByPrimaryKeySelective(BookingApproval record);

    /**
     * Update booking approval record by primary key
     * @param record Booking approval object
     * @return Number of affected rows
     */
    int updateByPrimaryKey(BookingApproval record);

    /**
     * Find all booking approvals
     * @param map Query conditions
     * @return List of booking approvals
     */
    List<BookingApproval> findAll(Map<String, Object> map);

    /**
     * Count all booking approvals
     * @param map Query conditions
     * @return Number of booking approvals
     */
    int findCount(Map<String, Object> map);

    /**
     * Find booking approvals by booking ID
     * @param bookingId Booking ID
     * @return List of booking approvals
     */
    List<BookingApproval> findByBookingId(Long bookingId);

    /**
     * Find booking approvals by approver ID
     * @param approverId Approver ID
     * @return List of booking approvals
     */
    List<BookingApproval> findByApproverId(Long approverId);
}