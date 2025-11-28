package com.sojson.conference.controller;

import com.sojson.common.controller.BaseController;
import com.sojson.conference.model.Booking;
import com.sojson.conference.service.BookingService;
import com.sojson.core.shiro.token.manager.TokenManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Booking Controller
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
@Controller
@Scope(value="prototype")
@RequestMapping("booking")
public class BookingController extends BaseController {

    @Resource
    private BookingService bookingService;

    /**
     * Booking list page
     * @return ModelAndView
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("conference/booking/list");
    }

    /**
     * Get booking list data
     * @param booking Booking object
     * @param request HttpServletRequest
     * @return Map<String, Object>
     * @throws Exception
     */
    @RequestMapping(value = "dataList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> dataList(Booking booking, HttpServletRequest request) throws Exception {
        Map<String, Object> params = prepareParams(booking, request);
        // Current user's bookings
        params.put("userId", TokenManager.getUserId());
        return bookingService.findByPage(params, pageNo, pageSize).getShowPageMap(params);
    }

    /**
     * Add booking page
     * @return ModelAndView
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("conference/booking/add");
    }

    /**
     * Save booking
     * @param booking Booking object
     * @return Map<String, Object>
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Booking booking) {
        resultMap.put("status", 200);
        try {
            // Check booking conflict
            int conflict = bookingService.checkConflict(booking.getRoomId(), booking.getStartTime(), booking.getEndTime(), null);
            if (conflict > 0) {
                resultMap.put("status", 400);
                resultMap.put("message", "Booking conflict! The room is already booked during this time.");
                return resultMap;
            }
            
            booking.setUserId(TokenManager.getUserId());
            booking.setCreateTime(new Date());
            booking.setStatus(Booking.STATUS_PENDING_APPROVAL);
            bookingService.insertSelective(booking);
            resultMap.put("message", "Booking submitted successfully! Waiting for approval.");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to submit booking: " + e.getMessage());
            logger.error("Failed to submit booking", e);
        }
        return resultMap;
    }

    /**
     * Edit booking page
     * @param id Booking ID
     * @return ModelAndView
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView view = new ModelAndView("conference/booking/edit");
        view.addObject("booking", bookingService.selectByPrimaryKey(id));
        return view;
    }

    /**
     * Update booking
     * @param booking Booking object
     * @return Map<String, Object>
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Booking booking) {
        resultMap.put("status", 200);
        try {
            // Check booking conflict
            int conflict = bookingService.checkConflict(booking.getRoomId(), booking.getStartTime(), booking.getEndTime(), booking.getId());
            if (conflict > 0) {
                resultMap.put("status", 400);
                resultMap.put("message", "Booking conflict! The room is already booked during this time.");
                return resultMap;
            }
            
            booking.setUpdateTime(new Date());
            bookingService.updateByPrimaryKeySelective(booking);
            resultMap.put("message", "Booking updated successfully!");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to update booking: " + e.getMessage());
            logger.error("Failed to update booking", e);
        }
        return resultMap;
    }

    /**
     * Delete booking
     * @param id Booking ID
     * @return Map<String, Object>
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        resultMap.put("status", 200);
        try {
            bookingService.deleteByPrimaryKey(id);
            resultMap.put("message", "Booking deleted successfully!");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to delete booking: " + e.getMessage());
            logger.error("Failed to delete booking", e);
        }
        return resultMap;
    }

    /**
     * Check booking conflict
     * @param roomId Room ID
     * @param startTime Start time
     * @param endTime End time
     * @param bookingId Booking ID (optional)
     * @return Map<String, Object>
     */
    @RequestMapping(value = "checkConflict", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkConflict(Long roomId, String startTime, String endTime, Long bookingId) {
        resultMap.put("status", 200);
        try {
            int conflict = bookingService.checkConflict(roomId, startTime, endTime, bookingId);
            resultMap.put("conflict", conflict);
            resultMap.put("message", conflict > 0 ? "Booking conflict found!" : "No booking conflict.");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to check booking conflict: " + e.getMessage());
            logger.error("Failed to check booking conflict", e);
        }
        return resultMap;
    }
}