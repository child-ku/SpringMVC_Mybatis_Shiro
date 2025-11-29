package com.sojson.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.document.dao.DocumentMapper;
import com.sojson.document.model.Document;
import com.sojson.document.service.DocumentService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.core.shiro.token.manager.TokenManager;

@Service
public class DocumentServiceImpl extends BaseMybatisDao<DocumentMapper> implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return documentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Document insert(Document entity) {
        documentMapper.insert(entity);
        return entity;
    }

    @Override
    public Document insertSelective(Document entity) {
        documentMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public Document selectByPrimaryKey(Long id) {
        return documentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Document entity) {
        return documentMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(Document entity) {
        return documentMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<Document> findByDirectoryId(Long directoryId) {
        return documentMapper.findByDirectoryId(directoryId);
    }

    @Override
    public List<Document> findByCreateBy(Long createBy) {
        return documentMapper.findByCreateBy(createBy);
    }

    @Override
    public List<Document> search(String keyword) {
        return documentMapper.search(keyword);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<Document> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> batchDelete(String ids) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int count = 0;
            String[] idArray = StringUtils.contains(ids, ",") ? ids.split(",") : new String[]{ids};
            for (String id : idArray) {
                if (StringUtils.isNotBlank(id)) {
                    documentMapper.deleteByPrimaryKey(Long.parseLong(id));
                    count++;
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "成功删除" + count + "条记录");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败：" + e.getMessage());
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateStatus(Long id, Integer status) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Document document = documentMapper.selectByPrimaryKey(id);
            if (document != null) {
                document.setStatus(status);
                documentMapper.updateByPrimaryKeySelective(document);
                resultMap.put("status", 200);
                resultMap.put("message", "状态更新成功");
            } else {
                resultMap.put("status", 404);
                resultMap.put("message", "文档不存在");
            }
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "状态更新失败：" + e.getMessage());
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> upload(Document document) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 设置创建者和更新者为当前登录用户
            Long currentUserId = TokenManager.getUserId();
            document.setCreateBy(currentUserId);
            document.setUpdateBy(currentUserId);
            // 设置创建时间和更新时间为当前时间
            document.setCreateTime(new java.util.Date());
            document.setUpdateTime(new java.util.Date());
            // 保存文档
            documentMapper.insertSelective(document);
            resultMap.put("status", 200);
            resultMap.put("message", "文档上传成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "文档上传失败：" + e.getMessage());
        }
        return resultMap;
    }
}
