package com.sojson.project.controller;

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
import com.sojson.project.model.Project;
import com.sojson.project.service.ProjectService;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.core.shiro.token.manager.TokenManager;

@Controller
@Scope(value="prototype")
@RequestMapping("project")
public class ProjectController extends BaseController {
    
    @Autowired
    private ProjectService projectService;
    
    /**
     * 项目列表
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        // 获取当前用户ID
        Long userId = TokenManager.getToken().getId();
        // 获取当前用户参与的项目
        List<Project> projects = projectService.selectProjectsByUserId(userId);
        modelMap.put("projects", projects);
        return "project/index";
    }
    
    /**
     * 项目详情
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, ModelMap modelMap) {
        Project project = projectService.selectByPrimaryKey(id);
        modelMap.put("project", project);
        return "project/detail";
    }
    
    /**
     * 创建项目页面
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "project/create";
    }
    
    /**
     * 保存项目
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Project project) {
        try {
            // 设置项目管理员为当前用户
            project.setManagerId(TokenManager.getToken().getId());
            projectService.insertSelective(project);
            return success("项目创建成功");
        } catch (Exception e) {
            return error("项目创建失败: " + e.getMessage());
        }
    }
    
    /**
     * 编辑项目页面
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        Project project = projectService.selectByPrimaryKey(id);
        modelMap.put("project", project);
        return "project/edit";
    }
    
    /**
     * 更新项目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Project project) {
        try {
            projectService.updateByPrimaryKeySelective(project);
            return success("项目更新成功");
        } catch (Exception e) {
            return error("项目更新失败: " + e.getMessage());
        }
    }
    
    /**
     * 关闭项目
     */
    @RequestMapping(value = "close/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> close(@PathVariable("id") Long id) {
        try {
            projectService.closeProject(id);
            return success("项目已关闭");
        } catch (Exception e) {
            return error("项目关闭失败: " + e.getMessage());
        }
    }
    
    /**
     * 暂停项目
     */
    @RequestMapping(value = "pause/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pause(@PathVariable("id") Long id) {
        try {
            projectService.pauseProject(id);
            return success("项目已暂停");
        } catch (Exception e) {
            return error("项目暂停失败: " + e.getMessage());
        }
    }
    
    /**
     * 恢复项目
     */
    @RequestMapping(value = "resume/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> resume(@PathVariable("id") Long id) {
        try {
            projectService.resumeProject(id);
            return success("项目已恢复");
        } catch (Exception e) {
            return error("项目恢复失败: " + e.getMessage());
        }
    }
    
    /**
     * 添加项目成员
     */
    @RequestMapping(value = "addMember", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMember(Long projectId, Long userId) {
        try {
            projectService.addProjectMember(projectId, userId);
            return success("成员添加成功");
        } catch (Exception e) {
            return error("成员添加失败: " + e.getMessage());
        }
    }
    
    /**
     * 移除项目成员
     */
    @RequestMapping(value = "removeMember", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeMember(Long projectId, Long userId) {
        try {
            projectService.removeProjectMember(projectId, userId);
            return success("成员移除成功");
        } catch (Exception e) {
            return error("成员移除失败: " + e.getMessage());
        }
    }
}