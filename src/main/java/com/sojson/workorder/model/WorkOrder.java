package com.sojson.workorder.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 工单实体类
 * 
 * @author AI Assistant
 * @version 1.0
 */
public class WorkOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Long statusId;
    private Long typeId;
    private Date createTime;
    private Date updateTime;
    
    public WorkOrder() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getStatusId() {
        return statusId;
    }
    
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
    
    public Long getTypeId() {
        return typeId;
    }
    
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
}