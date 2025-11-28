package com.sojson.conference.service.impl;

import com.sojson.conference.dao.BookingMapper;
import com.sojson.conference.model.Booking;
import com.sojson.conference.service.BookingService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Booking Service Implementation
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
@Service
public class BookingServiceImpl extends BaseMybatisDao<BookingMapper> implements BookingService {

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return bookingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Booking insert(Booking record) {
        bookingMapper.insert(record);
        return record;
    }

    @Override
    public Booking insertSelective(Booking record) {
        bookingMapper.insertSelective(record);
        return record;
    }

    @Override
    public Booking selectByPrimaryKey(Long id) {
        return bookingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Booking record) {
        return bookingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Booking record) {
        return bookingMapper.updateByPrimaryKey(record);
    }

    @Override
    public Pagination<Booking> findByPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage(map, pageNo, pageSize);
    }

    @Override
    public List<Booking> findAll(Map<String, Object> map) {
        return bookingMapper.findAll(map);
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        return bookingMapper.findByUserId(userId);
    }

    @Override
    public int checkConflict(Long roomId, String startTime, String endTime, Long bookingId) {
        return bookingMapper.checkConflict(roomId, startTime, endTime, bookingId);
    }
}