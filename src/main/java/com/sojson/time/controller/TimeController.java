package com.sojson.time.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sojson.common.controller.BaseController;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.time.model.TimeRecord;
import com.sojson.time.model.TimeModificationRequest;
import com.sojson.time.service.TimeService;

@Controller
@Scope(value = "prototype")
@RequestMapping("time")
public class TimeController extends BaseController {
    
    @Autowired
    private TimeService timeService;
    
    // Time Record Management
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {
        Long userId = TokenManager.getToken().getId();
        List<TimeRecord> timeRecords = timeService.getTimeRecordsByUserId(userId);
        map.put("timeRecords", timeRecords);
        return new ModelAndView("time/list");
    }
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("time/add");
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(TimeRecord timeRecord) {
        try {
            timeRecord.setUserId(TokenManager.getToken().getId());
            timeRecord.setCreateTime(new Date());
            timeRecord.setUpdateTime(new Date());
            timeService.insertTimeRecordSelective(timeRecord);
            return success("工时记录保存成功");
        } catch (Exception e) {
            return error("工时记录保存失败: " + e.getMessage());
        }
    }
    
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap map) {
        TimeRecord timeRecord = timeService.getTimeRecordById(id);
        map.put("timeRecord", timeRecord);
        return new ModelAndView("time/edit");
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(TimeRecord timeRecord) {
        try {
            timeRecord.setUpdateTime(new Date());
            timeService.updateTimeRecordByIdSelective(timeRecord);
            return success("工时记录更新成功");
        } catch (Exception e) {
            return error("工时记录更新失败: " + e.getMessage());
        }
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Long id) {
        try {
            timeService.deleteTimeRecordById(id);
            return success("工时记录删除成功");
        } catch (Exception e) {
            return error("工时记录删除失败: " + e.getMessage());
        }
    }
    
    // Time Modification Request Management
    @RequestMapping(value = "request/list", method = RequestMethod.GET)
    public ModelAndView requestList(ModelMap map) {
        Long userId = TokenManager.getToken().getId();
        List<TimeModificationRequest> requests = timeService.getTimeModificationRequestsByUserId(userId);
        map.put("requests", requests);
        return new ModelAndView("time/request/list");
    }
    
    @RequestMapping(value = "request/add/{timeRecordId}", method = RequestMethod.GET)
    public ModelAndView requestAdd(@PathVariable("timeRecordId") Long timeRecordId, ModelMap map) {
        TimeRecord timeRecord = timeService.getTimeRecordById(timeRecordId);
        map.put("timeRecord", timeRecord);
        return new ModelAndView("time/request/add");
    }
    
    @RequestMapping(value = "request/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> requestSave(TimeModificationRequest request) {
        try {
            request.setUserId(TokenManager.getToken().getId());
            request.setCreateTime(new Date());
            request.setUpdateTime(new Date());
            timeService.submitTimeModificationRequest(request);
            return success("工时修改申请提交成功");
        } catch (Exception e) {
            return error("工时修改申请提交失败: " + e.getMessage());
        }
    }
    
    @RequestMapping(value = "request/approve/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> requestApprove(@PathVariable("id") Long id, String reviewComment) {
        try {
            Long reviewerId = TokenManager.getToken().getId();
            timeService.approveTimeModificationRequest(id, reviewerId, reviewComment);
            return success("工时修改申请批准成功");
        } catch (Exception e) {
            return error("工时修改申请批准失败: " + e.getMessage());
        }
    }
    
    @RequestMapping(value = "request/reject/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> requestReject(@PathVariable("id") Long id, String reviewComment) {
        try {
            Long reviewerId = TokenManager.getToken().getId();
            timeService.rejectTimeModificationRequest(id, reviewerId, reviewComment);
            return success("工时修改申请拒绝成功");
        } catch (Exception e) {
            return error("工时修改申请拒绝失败: " + e.getMessage());
        }
    }
}