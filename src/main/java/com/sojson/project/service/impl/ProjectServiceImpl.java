package com.sojson.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sojson.project.dao.ProjectMapper;
import com.sojson.project.dao.ProjectMemberMapper;
import com.sojson.project.model.Project;
import com.sojson.project.model.ProjectMember;
import com.sojson.project.service.ProjectService;
import com.sojson.core.mybatis.BaseMybatisDao;

@Service
public class ProjectServiceImpl extends BaseMybatisDao<ProjectMapper> implements ProjectService {
    
    @Autowired
    private ProjectMapper projectMapper;
    
    @Autowired
    private ProjectMemberMapper projectMemberMapper;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return projectMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(Project record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return projectMapper.insert(record);
    }
    
    @Override
    public int insertSelective(Project record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return projectMapper.insertSelective(record);
    }
    
    @Override
    public Project selectByPrimaryKey(Long id) {
        return projectMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Project record) {
        record.setUpdateTime(new Date());
        return projectMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(Project record) {
        record.setUpdateTime(new Date());
        return projectMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<Project> selectAllProjects() {
        return projectMapper.selectAllProjects();
    }
    
    @Override
    public List<Project> selectProjectsByManagerId(Long managerId) {
        return projectMapper.selectProjectsByManagerId(managerId);
    }
    
    @Override
    public List<Project> selectProjectsByUserId(Long userId) {
        return projectMapper.selectProjectsByUserId(userId);
    }
    
    @Override
    public List<Project> selectProjectsByStatus(Integer status) {
        return projectMapper.selectProjectsByStatus(status);
    }
    
    @Override
    public List<Project> selectProjectsByCondition(Map<String, Object> condition) {
        return projectMapper.selectProjectsByCondition(condition);
    }
    
    @Override
    public void addProjectMember(Long projectId, Long userId) {
        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(userId);
        member.setJoinTime(new Date());
        projectMemberMapper.insertSelective(member);
    }
    
    @Override
    public void removeProjectMember(Long projectId, Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("userId", userId);
        projectMemberMapper.deleteByProjectIdAndUserId(params);
    }
    
    @Override
    public void closeProject(Long projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project != null) {
            project.setStatus(2); // 2: Completed
            project.setUpdateTime(new Date());
            projectMapper.updateByPrimaryKeySelective(project);
        }
    }
    
    @Override
    public void pauseProject(Long projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project != null) {
            project.setStatus(3); // 3: Paused
            project.setUpdateTime(new Date());
            projectMapper.updateByPrimaryKeySelective(project);
        }
    }
    
    @Override
    public void resumeProject(Long projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project != null) {
            project.setStatus(1); // 1: In progress
            project.setUpdateTime(new Date());
            projectMapper.updateByPrimaryKeySelective(project);
        }
    }
}