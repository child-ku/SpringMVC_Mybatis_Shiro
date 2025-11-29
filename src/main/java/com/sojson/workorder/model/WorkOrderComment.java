package com.sojson.workorder.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 工单评论实体类
 * 
 * @author AI Assistant
 * @version 1.0
 */
public class WorkOrderComment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long workOrderId;
    private Long userId;
    private String commentContent;
    private Date commentTime;
    
    public WorkOrderComment() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getWorkOrderId() {
        return workOrderId;
    }
    
    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getCommentContent() {
        return commentContent;
    }
    
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    
    public Date getCommentTime() {
        return commentTime;
    }
    
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}