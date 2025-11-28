package com.sojson.conference.service.impl;

import com.sojson.conference.dao.BookingApprovalMapper;
import com.sojson.conference.model.BookingApproval;
import com.sojson.conference.service.BookingApprovalService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Booking Approval Service Implementation
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
@Service
public class BookingApprovalServiceImpl extends BaseMybatisDao<BookingApprovalMapper> implements BookingApprovalService {

    @Autowired
    private BookingApprovalMapper bookingApprovalMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return bookingApprovalMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BookingApproval insert(BookingApproval record) {
        bookingApprovalMapper.insert(record);
        return record;
    }

    @Override
    public BookingApproval insertSelective(BookingApproval record) {
        bookingApprovalMapper.insertSelective(record);
        return record;
    }

    @Override
    public BookingApproval selectByPrimaryKey(Long id) {
        return bookingApprovalMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BookingApproval record) {
        return bookingApprovalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookingApproval record) {
        return bookingApprovalMapper.updateByPrimaryKey(record);
    }

    @Override
    public Pagination<BookingApproval> findByPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage(map, pageNo, pageSize);
    }

    @Override
    public List<BookingApproval> findAll(Map<String, Object> map) {
        return bookingApprovalMapper.findAll(map);
    }

    @Override
    public List<BookingApproval> findByBookingId(Long bookingId) {
        return bookingApprovalMapper.findByBookingId(bookingId);
    }

    @Override
    public List<BookingApproval> findByApproverId(Long approverId) {
        return bookingApprovalMapper.findByApproverId(approverId);
    }
}