package com.sojson.document.service;

import java.util.List;
import java.util.Map;

import com.sojson.document.model.Directory;
import com.sojson.core.mybatis.page.Pagination;

public interface DirectoryService {
    
    int deleteByPrimaryKey(Long id);

    Directory insert(Directory record);

    Directory insertSelective(Directory record);

    Directory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Directory record);

    int updateByPrimaryKey(Directory record);
    
    Pagination<Directory> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    
    List<Directory> findByParentId(Long parentId);
    
    List<Directory> findByCreateBy(Long createBy);
    
    Map<String, Object> deleteDirectoryById(String ids);
    
    List<Directory> getDirectoryTree(Long userId);
}
