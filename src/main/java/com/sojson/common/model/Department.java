package com.sojson.common.model;

import java.util.Date;
import java.util.List;

public class Department {
    private Long id;
    private String name;
    private Long parentId;
    private Date createTime;
    private Date updateTime;
    private List<Department> children;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public List<Department> getChildren() {
        return children;
    }
    
    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
