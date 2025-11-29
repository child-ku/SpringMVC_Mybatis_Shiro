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
import com.sojson.document.model.Document;
import com.sojson.document.service.DocumentService;

@Controller
@Scope(value="prototype")
@RequestMapping("document")
public class DocumentController extends BaseController {

    @Resource
    private DocumentService documentService;

    /**
     * Document management page
     * @return ModelAndView
     */
    @RequestMapping(value="index", method=RequestMethod.GET)
    public ModelAndView documentIndex() {
        return new ModelAndView("document/index");
    }

    /**
     * Universal page redirect
     * @param page Page name
     * @return ModelAndView
     */
    @RequestMapping(value="{page}", method=RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("page") String page) {
        return new ModelAndView(String.format("document/%s", page));
    }

    /**
     * Get document list by directory id
     * @param directoryId Directory id
     * @return Map<String, Object>
     */
    @RequestMapping(value="listByDirectory", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listByDirectory(Long directoryId) {
        try {
            List<Document> documents = documentService.findByDirectoryId(directoryId);
            resultMap.put("status", 200);
            resultMap.put("data", documents);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get document list by directory id: %s", directoryId);
            resultMap.put("status", 500);
            resultMap.put("message", "Failed to get document list");
        }
        return resultMap;
    }

    /**
     * Search documents
     * @param keyword Search keyword
     * @return Map<String, Object>
     */
    @RequestMapping(value="search", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> search(String keyword) {
        try {
            List<Document> documents = documentService.search(keyword);
            resultMap.put("status", 200);
            resultMap.put("data", documents);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to search documents with keyword: %s", keyword);
            resultMap.put("status", 500);
            resultMap.put("message", "Search failed");
        }
        return resultMap;
    }

    /**
     * Get document by id
     * @param id Document id
     * @return Map<String, Object>
     */
    @RequestMapping(value="get", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById(Long id) {
        try {
            Document document = documentService.selectByPrimaryKey(id);
            resultMap.put("status", 200);
            resultMap.put("data", document);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get document by id: %s", id);
            resultMap.put("status", 500);
            resultMap.put("message", "Failed to get document");
        }
        return resultMap;
    }

    /**
     * Save or update document
     * @param document Document object
     * @param request HttpServletRequest
     * @return Map<String, Object>
     */
    @RequestMapping(value="saveOrUpdate", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveOrUpdate(Document document, HttpServletRequest request) {
        try {
            Long currentUserId = TokenManager.getUserId();
            if (document.getId() == null) {
                // New document
                document.setCreateBy(currentUserId);
                document.setCreateTime(new java.util.Date());
                document.setUpdateBy(currentUserId);
                document.setUpdateTime(new java.util.Date());
                documentService.insertSelective(document);
                resultMap.put("status", 200);
                resultMap.put("message", "Document created successfully");
            } else {
                // Update document
                document.setUpdateBy(currentUserId);
                document.setUpdateTime(new java.util.Date());
                documentService.updateByPrimaryKeySelective(document);
                resultMap.put("status", 200);
                resultMap.put("message", "Document updated successfully");
            }
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to save or update document: %s", document.toString());
            resultMap.put("status", 500);
            resultMap.put("message", "Operation failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Delete document by id
     * @param id Document id
     * @return Map<String, Object>
     */
    @RequestMapping(value="delete", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteById(Long id) {
        try {
            documentService.deleteByPrimaryKey(id);
            resultMap.put("status", 200);
            resultMap.put("message", "Document deleted successfully");
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to delete document by id: %s", id);
            resultMap.put("status", 500);
            resultMap.put("message", "Delete failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Batch delete documents
     * @param ids Document ids separated by comma
     * @return Map<String, Object>
     */
    @RequestMapping(value="batchDelete", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        try {
            return documentService.batchDelete(ids);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to batch delete documents: %s", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "Batch delete failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Update document status
     * @param id Document id
     * @param status Document status
     * @return Map<String, Object>
     */
    @RequestMapping(value="updateStatus", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateStatus(Long id, Integer status) {
        try {
            return documentService.updateStatus(id, status);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to update document status for id: %s, status: %s", id, status);
            resultMap.put("status", 500);
            resultMap.put("message", "Status update failed: " + e.getMessage());
        }
        return resultMap;
    }

    /**
     * Get paginated document list
     * @param request HttpServletRequest
     * @return Pagination<Document>
     */
    @RequestMapping(value="list", method=RequestMethod.GET)
    @ResponseBody
    public Pagination<Document> list(HttpServletRequest request) {
        try {
            Document document = new Document();
            Map<String, Object> params = prepareParams(document, request);
            return documentService.findByPage(params, pageNo, pageSize);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "Failed to get paginated document list");
            return null;
        }
    }
}
