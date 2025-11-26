package com.sojson.project.dao;

import java.util.List;
import java.util.Map;
import com.sojson.project.model.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Project record);
    
    int insertSelective(Project record);
    
    Project selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Project record);
    
    int updateByPrimaryKey(Project record);
    
    List<Project> selectAllProjects();
    
    List<Project> selectProjectsByManagerId(Long managerId);
    
    List<Project> selectProjectsByUserId(Long userId);
    
    List<Project> selectProjectsByStatus(Integer status);
    
    List<Project> selectProjectsByCondition(Map<String, Object> condition);
}