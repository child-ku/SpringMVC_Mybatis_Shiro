package com.sojson.workorder.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.WorkOrderComment;
import com.sojson.workorder.service.WorkOrderCommentService;

@Controller
@Scope("prototype")
@RequestMapping("workorder/comment")
public class WorkOrderCommentController extends BaseController {

    @Resource
    private WorkOrderCommentService workOrderCommentService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(@RequestBody WorkOrderComment comment) {
        try {
            workOrderCommentService.insertSelective(comment);
            return result(200, "评论添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return result(500, "评论添加失败：" + e.getMessage());
        }
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        try {
            workOrderCommentService.deleteByPrimaryKey(id);
            return result(200, "评论删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return result(500, "评论删除失败：" + e.getMessage());
        }
    }
    
    @RequestMapping("list")
    @ResponseBody
    public List<WorkOrderComment> list(Long workOrderId) {
        return workOrderCommentService.selectByWorkOrderId(workOrderId);
    }

}