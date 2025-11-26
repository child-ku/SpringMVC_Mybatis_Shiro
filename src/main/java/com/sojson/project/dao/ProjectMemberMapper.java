package com.sojson.project.dao;

import java.util.List;
import java.util.Map;
import com.sojson.project.model.ProjectMember;

public interface ProjectMemberMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(ProjectMember record);
    
    int insertSelective(ProjectMember record);
    
    ProjectMember selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(ProjectMember record);
    
    int updateByPrimaryKey(ProjectMember record);
    
    int deleteByProjectIdAndUserId(Map<String, Object> params);
    
    List<ProjectMember> selectByProjectId(Long projectId);
    
    List<ProjectMember> selectByUserId(Long userId);
    
    List<Long> selectUserIdsByProjectId(Long projectId);
    
    List<Long> selectProjectIdsByUserId(Long userId);
}