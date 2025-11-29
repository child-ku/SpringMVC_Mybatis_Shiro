package com.sojson.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.document.dao.DirectoryMapper;
import com.sojson.document.model.Directory;
import com.sojson.document.service.DirectoryService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.core.shiro.token.manager.TokenManager;

@Service
public class DirectoryServiceImpl extends BaseMybatisDao<DirectoryMapper> implements DirectoryService {

    @Autowired
    private DirectoryMapper directoryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return directoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Directory insert(Directory entity) {
        directoryMapper.insert(entity);
        return entity;
    }

    @Override
    public Directory insertSelective(Directory entity) {
        directoryMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public Directory selectByPrimaryKey(Long id) {
        return directoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Directory entity) {
        return directoryMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(Directory entity) {
        return directoryMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<Directory> findByParentId(Long parentId) {
        return directoryMapper.findByParentId(parentId);
    }

    @Override
    public List<Directory> findByCreateBy(Long createBy) {
        return directoryMapper.findByCreateBy(createBy);
    }

    @Override
    public List<Directory> getDirectoryTree(Long userId) {
        return directoryMapper.getDirectoryTree(userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<Directory> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
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
                    directoryMapper.deleteByPrimaryKey(Long.parseLong(id));
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
    public Map<String, Object> createDirectory(Directory directory) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 设置创建者和更新者为当前登录用户
            Long currentUserId = TokenManager.getUserId();
            directory.setCreateBy(currentUserId);
            directory.setUpdateBy(currentUserId);
            // 设置创建时间和更新时间为当前时间
            directory.setCreateTime(new java.util.Date());
            directory.setUpdateTime(new java.util.Date());
            // 保存目录
            directoryMapper.insertSelective(directory);
            resultMap.put("status", 200);
            resultMap.put("message", "目录创建成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "目录创建失败：" + e.getMessage());
        }
        return resultMap;
    }
}
