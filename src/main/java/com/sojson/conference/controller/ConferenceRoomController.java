package com.sojson.conference.controller;

import com.sojson.common.controller.BaseController;
import com.sojson.conference.model.ConferenceRoom;
import com.sojson.conference.service.ConferenceRoomService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Conference Room Controller
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
@Controller
@Scope(value="prototype")
@RequestMapping("conferenceRoom")
public class ConferenceRoomController extends BaseController {

    @Resource
    private ConferenceRoomService conferenceRoomService;

    /**
     * Conference room list page
     * @return ModelAndView
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("conference/room/list");
    }

    /**
     * Get conference room list data
     * @param conferenceRoom Conference room object
     * @param request HttpServletRequest
     * @return Map<String, Object>
     * @throws Exception
     */
    @RequestMapping(value = "dataList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> dataList(ConferenceRoom conferenceRoom, HttpServletRequest request) throws Exception {
        Map<String, Object> params = prepareParams(conferenceRoom, request);
        return conferenceRoomService.findByPage(params, pageNo, pageSize).getShowPageMap(params);
    }

    /**
     * Add conference room page
     * @return ModelAndView
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("conference/room/add");
    }

    /**
     * Save conference room
     * @param conferenceRoom Conference room object
     * @return Map<String, Object>
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(ConferenceRoom conferenceRoom) {
        resultMap.put("status", 200);
        try {
            conferenceRoomService.insertSelective(conferenceRoom);
            resultMap.put("message", "Conference room added successfully!");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to add conference room: " + e.getMessage());
            logger.error("Failed to add conference room", e);
        }
        return resultMap;
    }

    /**
     * Edit conference room page
     * @param id Conference room ID
     * @return ModelAndView
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView view = new ModelAndView("conference/room/edit");
        view.addObject("conferenceRoom", conferenceRoomService.selectByPrimaryKey(id));
        return view;
    }

    /**
     * Update conference room
     * @param conferenceRoom Conference room object
     * @return Map<String, Object>
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(ConferenceRoom conferenceRoom) {
        resultMap.put("status", 200);
        try {
            conferenceRoomService.updateByPrimaryKeySelective(conferenceRoom);
            resultMap.put("message", "Conference room updated successfully!");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to update conference room: " + e.getMessage());
            logger.error("Failed to update conference room", e);
        }
        return resultMap;
    }

    /**
     * Delete conference room
     * @param id Conference room ID
     * @return Map<String, Object>
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        resultMap.put("status", 200);
        try {
            conferenceRoomService.deleteByPrimaryKey(id);
            resultMap.put("message", "Conference room deleted successfully!");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to delete conference room: " + e.getMessage());
            logger.error("Failed to delete conference room", e);
        }
        return resultMap;
    }

    /**
     * Find available conference rooms by time range
     * @param startTime Start time
     * @param endTime End time
     * @return List<ConferenceRoom>
     */
    @RequestMapping(value = "available", method = RequestMethod.GET)
    @ResponseBody
    public List<ConferenceRoom> findAvailableRooms(String startTime, String endTime) {
        return conferenceRoomService.findAvailableRooms(startTime, endTime);
    }
}