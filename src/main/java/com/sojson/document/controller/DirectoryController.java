package com.sojson.document.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.document.model.Directory;
import com.sojson.document.service.DirectoryService;

@Controller
@Scope(value="prototype")
@RequestMapping("directory")
public class DirectoryController extends BaseController {

    @Resource
    private DirectoryService directoryService;

    /**
     * Get directory tree
     * @return Map<String, Object>
     */
    @RequestMapping(value="tree", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDirectoryTree() {
        try {
            Long currentUserId = TokenManager.getUserId();
            List<Directory> directoryTree = directoryService.getDirectoryTree(currentUserId);
            resultMap.put("status", 200);
            resultMap.put("data", directoryTree);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get directory tree");
            resultMap.put("status", 500);
            resultMap.put("message", "Failed to get directory tree");
        }
        return resultMap;
    }

    /**
     * Get directory by id
     * @param id Directory id
     * @return Map<String, Object>
     */
    @RequestMapping(value="get", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById(Long id) {
        try {
            Directory directory = directoryService.selectByPrimaryKey(id);
            resultMap.put("status", 200);
            resultMap.put("data", directory);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get directory by id: %s", id);
            resultMap.put("status", 500);
            resultMap.put("message", "Failed to get directory");
        }
        return resultMap;
    }

    /**
     * Get subdirectories by parent id
     * @param parentId Parent directory id
     * @return Map<String, Object>
     */
    @RequestMapping(value="children", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getChildren(Long parentId) {
        try {
            List<Directory> children = directoryService.findByParentId(parentId);
            resultMap.put("status", 200);
            resultMap.put("data", children);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get subdirectories by parent id: %s", parentId);
            resultMap.put("status", 500);
            resultMap.put("message", "Failed to get subdirectories");
        }
        return resultMap;
    }

    /**
     * Save or update directory
     * @param directory Directory object
     * @param request HttpServletRequest
     * @return Map<String, Object>
     */
    @RequestMapping(value="saveOrUpdate", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveOrUpdate(Directory directory, HttpServletRequest request) {
        try {
            Long currentUserId = TokenManager.getUserId();
            if (directory.getId() == null) {
                // New directory
                directory.setCreateBy(currentUserId);
                directory.setCreateTime(new java.util.Date());
                directory.setUpdateBy(currentUserId);
                directory.setUpdateTime(new java.util.Date());
                directoryService.insertSelective(directory);
                resultMap.put("status", 200);
                resultMap.put("message", "Directory created successfully");
            } else {
                // Update directory
                directory.setUpdateBy(currentUserId);
                directory.setUpdateTime(new java.util.Date());
                directoryService.updateByPrimaryKeySelective(directory);
                resultMap.put("status", 200);
                resultMap.put("message", "Directory updated successfully");
            }
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to save or update directory: %s", directory.toString());
            resultMap.put("status", 500);
            resultMap.put("message", "Operation failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Delete directory by id
     * @param id Directory id
     * @return Map<String, Object>
     */
    @RequestMapping(value="delete", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteById(Long id) {
        try {
            directoryService.deleteByPrimaryKey(id);
            resultMap.put("status", 200);
            resultMap.put("message", "Directory deleted successfully");
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to delete directory by id: %s", id);
            resultMap.put("status", 500);
            resultMap.put("message", "Delete failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Batch delete directories
     * @param ids Directory ids separated by comma
     * @return Map<String, Object>
     */
    @RequestMapping(value="batchDelete", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        try {
            return directoryService.batchDelete(ids);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to batch delete directories: %s", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "Batch delete failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Get paginated directory list
     * @param request HttpServletRequest
     * @return Pagination<Directory>
     */
    @RequestMapping(value="list", method=RequestMethod.GET)
    @ResponseBody
    public Pagination<Directory> list(HttpServletRequest request) {
        try {
            Directory directory = new Directory();
            Map<String, Object> params = prepareParams(directory, request);
            return directoryService.findByPage(params, pageNo, pageSize);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get paginated directory list");
            return null;
        }
    }
}
