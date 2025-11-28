package com.sojson.conference.service;

import com.sojson.conference.model.BookingApproval;
import com.sojson.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Booking Approval Service Interface
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public interface BookingApprovalService {
    /**
     * Delete booking approval by primary key
     * @param id Booking approval ID
     * @return Number of affected rows
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Insert booking approval record
     * @param record Booking approval object
     * @return Booking approval object
     */
    BookingApproval insert(BookingApproval record);

    /**
     * Insert selective booking approval record
     * @param record Booking approval object
     * @return Booking approval object
     */
    BookingApproval insertSelective(BookingApproval record);

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
     * @param pageNo Page number
     * @param pageSize Page size
     * @return Pagination result
     */
    Pagination<BookingApproval> findByPage(Map<String, Object> map, Integer pageNo, Integer pageSize);

    /**
     * Find all booking approvals without pagination
     * @param map Query conditions
     * @return List of booking approvals
     */
    List<BookingApproval> findAll(Map<String, Object> map);

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