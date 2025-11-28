package com.sojson.conference.service.impl;

import com.sojson.conference.dao.ConferenceRoomMapper;
import com.sojson.conference.model.ConferenceRoom;
import com.sojson.conference.service.ConferenceRoomService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Conference Room Service Implementation
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
@Service
public class ConferenceRoomServiceImpl extends BaseMybatisDao<ConferenceRoomMapper> implements ConferenceRoomService {

    @Autowired
    private ConferenceRoomMapper conferenceRoomMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return conferenceRoomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ConferenceRoom insert(ConferenceRoom record) {
        conferenceRoomMapper.insert(record);
        return record;
    }

    @Override
    public ConferenceRoom insertSelective(ConferenceRoom record) {
        conferenceRoomMapper.insertSelective(record);
        return record;
    }

    @Override
    public ConferenceRoom selectByPrimaryKey(Long id) {
        return conferenceRoomMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ConferenceRoom record) {
        return conferenceRoomMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ConferenceRoom record) {
        return conferenceRoomMapper.updateByPrimaryKey(record);
    }

    @Override
    public Pagination<ConferenceRoom> findByPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage(map, pageNo, pageSize);
    }

    @Override
    public List<ConferenceRoom> findAll(Map<String, Object> map) {
        return conferenceRoomMapper.findAll(map);
    }

    @Override
    public List<ConferenceRoom> findAvailableRooms(String startTime, String endTime) {
        return conferenceRoomMapper.findAvailableRooms(startTime, endTime);
    }
}