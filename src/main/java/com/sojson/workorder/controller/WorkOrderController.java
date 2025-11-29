package com.sojson.workorder.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.WorkOrder;
import com.sojson.common.model.WorkOrderComment;
import com.sojson.workorder.service.WorkOrderCommentService;
import com.sojson.workorder.service.WorkOrderService;

@Controller
@Scope(value="prototype")
@RequestMapping("workorder")
public class WorkOrderController extends BaseController {

    @Resource
    private WorkOrderService workOrderService;
    
    @Resource
    private WorkOrderCommentService workOrderCommentService;

    /**
     * 工单管理首页
     * @return
     */
    @RequestMapping(value="index",method=RequestMethod.GET)
    public ModelAndView workOrderIndex(){
        return new ModelAndView("workorder/index");
    }
    
    /**
     * 通用页面跳转
     * @param page
     * @return
     */
    @RequestMapping(value="{page}",method=RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("page")String page){
        return new ModelAndView(String.format("workorder/%s", page));
    }
    
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(WorkOrder workOrder) {
        try {
            workOrderService.insertSelective(workOrder);
            return result(200, "工单创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return result(500, "工单创建失败：" + e.getMessage());
        }
    }
    
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(WorkOrder workOrder) {
        try {
            workOrderService.updateByPrimaryKeySelective(workOrder);
            return result(200, "工单更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return result(500, "工单更新失败：" + e.getMessage());
        }
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        try {
            workOrderService.deleteByPrimaryKey(id);
            return result(200, "工单删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return result(500, "工单删除失败：" + e.getMessage());
        }
    }
    
    @RequestMapping("detail")
    public ModelAndView detail(Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("workorder/detail");
        
        // 获取工单详情
        WorkOrder workOrder = workOrderService.selectByPrimaryKey(id);
        view.addObject("workOrder", workOrder);
        
        // 获取工单评论
        List<WorkOrderComment> comments = workOrderCommentService.selectByWorkOrderId(id);
        view.addObject("comments", comments);
        
        return view;
    }
    
    @RequestMapping("list")
    @ResponseBody
    public List<WorkOrder> list(@RequestParam Map<String, Object> params) {
        return workOrderService.selectByCondition(params);
    }
    
    @RequestMapping("count")
    @ResponseBody
    public int count(@RequestParam Map<String, Object> params) {
        return workOrderService.countByCondition(params);
    }
}