package com.sojson.conference.controller;

import com.sojson.common.controller.BaseController;
import com.sojson.conference.model.Booking;
import com.sojson.conference.model.BookingApproval;
import com.sojson.conference.service.BookingApprovalService;
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
 * Booking Approval Controller
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
@Controller
@Scope(value="prototype")
@RequestMapping("bookingApproval")
public class BookingApprovalController extends BaseController {

    @Resource
    private BookingApprovalService bookingApprovalService;
    @Resource
    private BookingService bookingService;

    /**
     * Booking approval list page
     * @return ModelAndView
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("conference/approval/list");
    }

    /**
     * Get booking approval list data
     * @param bookingApproval Booking approval object
     * @param request HttpServletRequest
     * @return Map<String, Object>
     * @throws Exception
     */
    @RequestMapping(value = "dataList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> dataList(BookingApproval bookingApproval, HttpServletRequest request) throws Exception {
        Map<String, Object> params = prepareParams(bookingApproval, request);
        // Current user's approvals
        params.put("approverId", TokenManager.getUserId());
        return bookingApprovalService.findByPage(params, pageNo, pageSize).getShowPageMap(params);
    }

    /**
     * Approve booking
     * @param id Booking approval ID
     * @param approvalStatus Approval status
     * @param comment Comment
     * @return Map<String, Object>
     */
    @RequestMapping(value = "approve", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> approve(Long id, Integer approvalStatus, String comment) {
        resultMap.put("status", 200);
        try {
            BookingApproval bookingApproval = bookingApprovalService.selectByPrimaryKey(id);
            if (bookingApproval == null) {
                resultMap.put("status", 400);
                resultMap.put("message", "Booking approval not found!");
                return resultMap;
            }
            
            bookingApproval.setApprovalStatus(approvalStatus);
            bookingApproval.setComment(comment);
            bookingApproval.setApprovalTime(new Date());
            bookingApproval.setApproverId(TokenManager.getUserId());
            bookingApprovalService.updateByPrimaryKeySelective(bookingApproval);
            
            // Update booking status
            Booking booking = bookingService.selectByPrimaryKey(bookingApproval.getBookingId());
            if (booking != null) {
                booking.setStatus(approvalStatus == BookingApproval.STATUS_APPROVED ? Booking.STATUS_APPROVED : Booking.STATUS_REJECTED);
                bookingService.updateByPrimaryKeySelective(booking);
            }
            
            resultMap.put("message", approvalStatus == BookingApproval.STATUS_APPROVED ? "Booking approved successfully!" : "Booking rejected successfully!");
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "Failed to process booking approval: " + e.getMessage());
            logger.error("Failed to process booking approval", e);
        }
        return resultMap;
    }
}