package com.sojson.document.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Directory model
 */
public class Directory implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name; // Directory name
    private Long parentId; // Parent directory ID
    private Long createBy; // User ID who created the directory
    private Date createTime; // Creation time
    private Long updateBy; // User ID who last updated the directory
    private Date updateTime; // Last update time
    
    // Constructors
    public Directory() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    
    public Long getCreateBy() { return createBy; }
    public void setCreateBy(Long createBy) { this.createBy = createBy; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    
    public Long getUpdateBy() { return updateBy; }
    public void setUpdateBy(Long updateBy) { this.updateBy = updateBy; }
    
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
