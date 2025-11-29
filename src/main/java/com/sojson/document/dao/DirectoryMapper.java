package com.sojson.document.dao;

import java.util.List;

import com.sojson.document.model.Directory;

public interface DirectoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Directory record);

    int insertSelective(Directory record);

    Directory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Directory record);

    int updateByPrimaryKey(Directory record);
    
    List<Directory> findByParentId(Long parentId);
    
    List<Directory> findByCreateBy(Long createBy);
    
    List<Directory> getDirectoryTree(Long userId);
}
